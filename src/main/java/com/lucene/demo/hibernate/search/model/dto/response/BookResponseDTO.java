package com.lucene.demo.hibernate.search.model.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookResponseDTO {
    private Integer id;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String isbn;
    private Double rating;
    private String publisher;
    private String genre;
    private List<String> tags;
}
