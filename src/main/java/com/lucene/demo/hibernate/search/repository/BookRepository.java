package com.lucene.demo.hibernate.search.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
;
import com.lucene.demo.hibernate.search.model.Book;

@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    Optional<Book> findByBookTitle(@Param("bookTitle") String bookTitle);
    Optional<Book> findByIsbn(@Param("isbn") String isbn);
    List<Book> findByRating(@Param("rating") Double rating);
}
