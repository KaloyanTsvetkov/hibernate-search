package com.lucene.demo.hibernate.search.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lucene.demo.hibernate.search.model.Genre;

@RepositoryRestResource
public interface GenreRepository extends PagingAndSortingRepository<Genre, Integer> {
    Optional<Genre> findByGenreName(@Param("genreName") String genreName);
}
