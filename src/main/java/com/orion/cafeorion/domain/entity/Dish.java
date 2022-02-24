package com.orion.cafeorion.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Dmitriy
 * @since 26.01.2022
 */
@Entity
@Table(name = "dishes")
@Getter
@Setter
public class Dish extends BaseEntity {

    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Integer price;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategories_id")
    private Subcategory subcategory;

}
