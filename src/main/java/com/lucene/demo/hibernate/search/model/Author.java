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

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author implements Serializable {
    @Serial
    private static final long serialVersionUID = 3216650834203485043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private Integer authorId;

    @Field
    @Column(name = "first_name")
    private String firstName;

    @Field
    @Column(name = "middle_name")
    private String middleName;

    @Field
    @Column(name = "last_name")
    private String lastName;

    @ContainedIn
    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> recipes = new LinkedHashSet<>();
}
