package com.lucene.demo.hibernate.search.model;

import java.util.LinkedHashSet;
import java.util.Set;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre implements Serializable {
    @Serial
    private static final long serialVersionUID = -2712937178047211920L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "genre_name")
    private String genreName;

    @ManyToMany
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> recipes = new LinkedHashSet<>();
}
