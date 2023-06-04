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
import javax.persistence.OneToMany;
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
@Table(name = "publishers", indexes = {
        @Index(name = "publisher_name_unq", columnList = "publisher_name", unique = true)
})
public class Publisher implements Serializable {
    @Serial
    private static final long serialVersionUID = -7980711347211937929L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false)
    private Integer publisherId;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new LinkedHashSet<>();
}
