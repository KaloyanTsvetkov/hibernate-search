package com.lucene.demo.hibernate.search.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lucene.demo.hibernate.search.model.Tag;

@RepositoryRestResource
public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
    Optional<Tag> findByTagName(@Param("tagName") String tagName);
}
