package com.orion.cafeorion.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Dmitriy
 * @since 03.02.2022
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    Dish dish;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "order_time")
    String orderTime;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    Status status;

    public enum Status {
        CREATED,
        PREPARING,
        READY,
        PAID
    }
}
