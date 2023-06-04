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
import javax.persistence.Index;
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
@Table(name = "tags", indexes = {
        @Index(name = "tag_name_unq", columnList = "tag_name", unique = true)
})
public class Tag implements Serializable {
    @Serial
    private static final long serialVersionUID = 4459475510051436945L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @ManyToMany
    @JoinTable(name = "recipe_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> recipes = new LinkedHashSet<>();
}
