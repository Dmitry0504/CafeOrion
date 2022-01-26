package com.orion.cafeorion.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */
@Entity
@Table(name = "subcategories")
@Getter
@Setter
public class Subcategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Category category;

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
