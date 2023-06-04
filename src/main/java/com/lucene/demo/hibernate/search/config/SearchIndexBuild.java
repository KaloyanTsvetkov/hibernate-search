package com.lucene.demo.hibernate.search.config;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lucene.demo.hibernate.search.exception.IndexException;

@Transactional
@Component
public class SearchIndexBuild {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchIndexBuild.class);

    private final EntityManager entityManager;

    public SearchIndexBuild(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Using an EntityManager (JPA) to rebuild an index.
     *
     * @throws IndexException when we have interruptions during the build
     */
    public void indexPersistedData() throws IndexException {
        try {
            LOGGER.info("Start building Lucene indexes.");
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
            LOGGER.info("Building Lucene indexes completed.");
        } catch (InterruptedException e) {
            LOGGER.error("Error during indexing the data.", e);
            throw new IndexException("Index Interrupted.", e);
        }
    }
}
