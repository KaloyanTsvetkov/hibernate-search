package com.lucene.demo.hibernate.search.model;

import java.util.Set;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;

import static org.hibernate.search.annotations.Index.YES;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Indexed
@Table(name = "books")
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 8705240176508100999L;

    @SortableField
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Field(index = YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @Field(index = YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Field(index = YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "published_date", nullable = false)
    private java.sql.Date publishedDate;

    @Column(name = "total_pages")
    private Integer totalPages;

    @Field(index = YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "rating")
    private Double rating;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @IndexedEmbedded
    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    @IndexedEmbedded
    @ManyToMany
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @IndexedEmbedded
    @ManyToMany
    @JoinTable(
            name = "book_tags",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}
