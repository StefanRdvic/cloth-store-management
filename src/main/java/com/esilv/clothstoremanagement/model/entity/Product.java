package com.esilv.clothstoremanagement.model.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * This class represents a product
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Entity
@Table(name="Product")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public abstract class Product implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "retailPrice")
    private double retailPrice;
    @Column(name = "resellPrice")
    private double resellPrice;
    @Column(name = "stock")
    private int stock;
    @Column(name = "discount")
    private double discount;
}
