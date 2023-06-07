package com.lucene.demo.hibernate.search.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import com.lucene.demo.hibernate.search.model.Book;
import com.lucene.demo.hibernate.search.model.dto.Mapper;
import com.lucene.demo.hibernate.search.model.dto.request.SearchRequest;
import com.lucene.demo.hibernate.search.model.dto.response.BookResponseDTO;
import com.lucene.demo.hibernate.search.service.SearchService;

@AllArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;
    private final Mapper mapper;

    @PostMapping()
    public List<BookResponseDTO> getRecipeComplexSearch(@RequestBody SearchRequest searchRequest){
        List<Book> books = searchService.getBooksComplexSearch(searchRequest);
        return mapper.queryResultToResponseDTOList(books);
    }
}
