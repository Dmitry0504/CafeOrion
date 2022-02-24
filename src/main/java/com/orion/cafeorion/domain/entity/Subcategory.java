package com.orion.cafeorion.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy
 * @since 21.01.2022
 */
@Entity
@Table(name = "subcategories")
@Getter
@Setter
public class Subcategory extends BaseEntity{

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Category category;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "subcategory", fetch = FetchType.EAGER)
    private List<Dish> dishList;


    public void addNewDishToList(Dish dish) {
        if (dishList == null) {
            dishList = new ArrayList<>();
        }
        dishList.add(dish);
        dish.setSubcategory(this);
    }

}
