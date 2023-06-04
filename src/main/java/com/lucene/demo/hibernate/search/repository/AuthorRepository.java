package com.lucene.demo.hibernate.search.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lucene.demo.hibernate.search.model.Author;

@RepositoryRestResource
public interface AuthorRepository extends PagingAndSortingRepository<Author, Integer> {
    Optional<Author> findByFirstName(@Param("firstName") String firstName);
    Optional<Author> findByLastName(@Param("lastName") String lastName);
}
