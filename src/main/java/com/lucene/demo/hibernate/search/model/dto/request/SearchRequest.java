package com.lucene.demo.hibernate.search.model.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Request object used for searches
 */
@Getter
@Setter
public class SearchRequest {
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private Double rating;
    private String publisher;
    private String genre;
    private List<String> tags;
}
