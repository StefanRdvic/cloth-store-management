package com.esilv.clothstoremanagement.model;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Product")
public abstract class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float retailPrice;
    private float resellPrice;
    private int stock;
    private float discount;
}
