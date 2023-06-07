package com.lucene.demo.hibernate.search.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lucene.demo.hibernate.search.model.Author;
import com.lucene.demo.hibernate.search.model.Book;
import com.lucene.demo.hibernate.search.model.Tag;
import com.lucene.demo.hibernate.search.model.dto.response.BookResponseDTO;

@Component
public class Mapper {

    public List<BookResponseDTO> queryResultToResponseDTOList(List<Book> books){
        List<BookResponseDTO> response = new ArrayList<>();

        for (Book book : books) {
            BookResponseDTO bookDTO = new BookResponseDTO();
            bookDTO.setId(book.getBookId());
            bookDTO.setTitle(book.getBookTitle());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setRating(book.getRating());
            bookDTO.setPublisher(book.getPublisher().getPublisherName());
            bookDTO.setGenre(book.getGenres().stream().toList().get(0).getGenreName());

            Author auth = book.getAuthors().stream().toList().get(0);
            bookDTO.setAuthorFirstName(auth.getFirstName());
            bookDTO.setAuthorLastName(auth.getLastName());

            List<String> tags = new ArrayList<>();
            for (Tag tag : book.getTags()) {
                tags.add(tag.getTagName());
            }
            bookDTO.setTags(tags);
            response.add(bookDTO);
        }

        return  response;
    }
}
