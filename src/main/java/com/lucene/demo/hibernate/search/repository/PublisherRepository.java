package com.lucene.demo.hibernate.search.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lucene.demo.hibernate.search.model.Publisher;

@RepositoryRestResource
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Integer> {
    Optional<Publisher> findByPublisherName(@Param("publisherName") String publisherName);
}
