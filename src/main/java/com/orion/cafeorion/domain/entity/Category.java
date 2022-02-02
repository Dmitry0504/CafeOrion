package com.orion.cafeorion.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy
 * @since  19.01.2022
 */

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseEntity {

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "category", fetch = FetchType.EAGER)
    private List<Subcategory> subcategoryList;

    public void addNewSubcategoryToList(Subcategory subcategory) {
        if (subcategoryList == null) {
            subcategoryList = new ArrayList<>();
        }
        subcategoryList.add(subcategory);
        subcategory.setCategory(this);
    }

}
