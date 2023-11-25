package com.esilv.clothstoremanagement.model.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Entity
@Table(name="Product")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AllArgsConstructor
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
    private float retailPrice;
    @Column(name = "resellPrice")
    private float resellPrice;
    @Column(name = "stock")
    private int stock;
    @Column(name = "discount")
    private float discount;
}
