package com.lucene.demo.hibernate.search.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.lucene.demo.hibernate.search.model.Book;
import com.lucene.demo.hibernate.search.model.dto.request.SearchRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service used for executing searches by using Lucene queries & JPA.
 */
@Component
@RequiredArgsConstructor
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    private static final int FUZZY_DISTANCE = 2;
    private static final String TITLE_FIELD = "bookTitle";
    private static final String ISBN_FIELD = "isbn";
    private static final String RATING_FIELD = "rating";
    private static final String PUBLISHER_NAME_FIELD = "publisher.publisherName";
    private static final String GENRE_NAME_FIELD = "genres.genreName";
    private static final String TAG_NAME_FIELD = "tags.tagName";
    private static final String AUTHOR_FIRST_NAME_FIELD = "authors.firstName";
    private static final String AUTHOR_LAST_NAME_FIELD = "authors.lastName";
    private static final String BOOK_ID_FIELD = "bookId";
    private static final int MAX_RESULTS_LIMIT = 10;

    private final EntityManager entityManager;

    /**
     * Generates Lucene query based on the user input and execute the search.
     * Queries are using fuzzy search - check for differences in last chars.
     * If no input params - returning the latest 10 recipes.
     *
     * @param bookCriteria RecipeRequest user input
     * @return List<Book> result from the Lucene indexes
     */
    public List<Book> getBooksComplexSearch(SearchRequest bookCriteria){
        LOGGER.info("Generating Lucene query for request: {}", bookCriteria);
        boolean hasParams = false;

        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        BooleanJunction booleanJunction = queryBuilder.bool();

        if (bookCriteria.getTitle()!=null && !bookCriteria.getTitle().isBlank()) {
            LOGGER.debug("Adding title query.");
            Query titleQuery = generateSubQuery(fullTextEntityManager,
                                                bookCriteria.getTitle(),
                                                TITLE_FIELD);
            booleanJunction.should(titleQuery);
            hasParams = true;
        }

        if (bookCriteria.getAuthorFirstName()!=null && !bookCriteria.getAuthorFirstName().isBlank()) {
            LOGGER.debug("Adding author first name query.");
            Query authorFNQuery = generateSubQuery(fullTextEntityManager,
                                                   bookCriteria.getAuthorFirstName(),
                                                   AUTHOR_FIRST_NAME_FIELD);
            booleanJunction.should(authorFNQuery);
            hasParams = true;
        }

        if (bookCriteria.getAuthorLastName()!=null && !bookCriteria.getAuthorLastName().isBlank()) {
            LOGGER.debug("Adding author last name query.");
            Query authorLNQuery = generateSubQuery(fullTextEntityManager,
                                                   bookCriteria.getAuthorLastName(),
                                                   AUTHOR_LAST_NAME_FIELD);
            booleanJunction.should(authorLNQuery);
            hasParams = true;
        }

        if (bookCriteria.getIsbn()!=null && !bookCriteria.getIsbn().isBlank()) {
            LOGGER.debug("Adding isbn query.");
            Query isbnQuery = generateWildcardSubQuery(fullTextEntityManager,
                                               bookCriteria.getIsbn(),
                                               ISBN_FIELD);
            booleanJunction.should(isbnQuery);
            hasParams = true;
        }

        if (bookCriteria.getRating()!=null && bookCriteria.getRating()>0) {
            LOGGER.debug("Adding rating query.");
            Query ratingQuery = generateDoubleSubQuery(fullTextEntityManager,
                                                     bookCriteria.getRating(),
                                                     RATING_FIELD);
            // NOT is used after must
            // booleanJunction.must(ratingQuery).not()
            booleanJunction.must(ratingQuery);
            hasParams = true;
        }

        if (bookCriteria.getPublisher()!=null && !bookCriteria.getPublisher().isBlank()) {
            LOGGER.debug("Adding publisher query.");
            Query publisherQuery = generateNoFuzzySubQuery(fullTextEntityManager,
                                                    bookCriteria.getPublisher(),
                                                    PUBLISHER_NAME_FIELD);
            booleanJunction.should(publisherQuery);
            hasParams = true;
        }

        if (bookCriteria.getGenre()!=null && !bookCriteria.getGenre().isBlank()) {
            LOGGER.debug("Adding genre query.");
            Query generateSubQuery = generateSubQuery(fullTextEntityManager,
                                                      bookCriteria.getGenre(),
                                                      GENRE_NAME_FIELD);
            booleanJunction.should(generateSubQuery);
            hasParams = true;
        }

        if (bookCriteria.getTags()!=null && !bookCriteria.getTags().isEmpty()) {
            LOGGER.debug("Adding tags query. {}", bookCriteria.getTags());
            // This is NOT needed - we set analyze = Analyze.YES
            for (String tag : bookCriteria.getTags()) {
                Query generateInSubQuery = generateSubQuery(fullTextEntityManager,
                                              tag,
                                              TAG_NAME_FIELD);
                booleanJunction.should(generateInSubQuery);
            }
            hasParams = true;
        }

        // if no user input returns latest 10 books
        if (!hasParams) {
            LOGGER.info("No params. Executing FullTextQuery.");
            FullTextQuery fullTextQuery = generateDefaultQuery(fullTextEntityManager);
            return (List<Book>) fullTextQuery.getResultList();
        }

        Query finalQuery = booleanJunction.createQuery();

        // Step 3
        FullTextQuery fullTextQuery = fullTextEntityManager
                .createFullTextQuery(finalQuery, Book.class);
        fullTextQuery.setSort(queryBuilder.sort().byScore().createSort());
        LOGGER.info("Executing the Lucene query.");

        // Step 4
        return (List<Book>) fullTextQuery.getResultList();
    }

    /**
     * Keyword query
     */
    private Query generateNoFuzzySubQuery(FullTextEntityManager fullTextEntityManager,
                                          String matchingCriteria, String fieldName) {
        // Step 1
        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        // Step 2
        return queryBuilder.keyword()
                           .onField(fieldName)
                           .matching(matchingCriteria)
                           .createQuery();
    }

    /**
     * Fuzzy query
     */
    private Query generateSubQuery(FullTextEntityManager fullTextEntityManager,
                                          String matchingCriteria, String fieldName) {
        // Step 1
        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        // Step 2
        return queryBuilder.keyword().fuzzy()
                           .withEditDistanceUpTo(FUZZY_DISTANCE)
                           .onField(fieldName)
                           .matching(matchingCriteria)
                           .createQuery();
    }

    /**
     * Wildcard query
     * ? represents a single character and * represents multiple characters
     */
    private Query generateWildcardSubQuery(FullTextEntityManager fullTextEntityManager,
                                          String matchingCriteria, String fieldName) {
        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        return queryBuilder.keyword()
                           .wildcard()
                           .onField(fieldName)
                           .matching(matchingCriteria)
                           .createQuery();
    }

    /**
     * Range query
     */
    private Query generateDoubleSubQuery(FullTextEntityManager fullTextEntityManager,
                                   Double matchingCriteria, String fieldName) {
        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        return queryBuilder.range()
                           .onField(fieldName)
                           .above(matchingCriteria)
                           .createQuery();
    }

    /**
     * All with sorting query
     * if no data in the user request returns latest 10 books.
     * @param fullTextEntityManager
     * @return
     */
    private FullTextQuery generateDefaultQuery(FullTextEntityManager fullTextEntityManager){
        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        Query query =  queryBuilder.all()
                                   .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query,Book.class);
        fullTextQuery.setSort(queryBuilder.sort()
                                          .byField("bookId_sort")
                                          .desc()
                                          .createSort());
        fullTextQuery.setMaxResults(MAX_RESULTS_LIMIT);

        return fullTextQuery;
    }

    /**
     * Phrase query
     */
    private Query generatePhraseSubQuery(FullTextEntityManager fullTextEntityManager,
                                         String matchingCriteria, String fieldName) {
        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        return queryBuilder.phrase()
                           .onField(fieldName)
                           .sentence(matchingCriteria)
                           .createQuery();
    }
}
