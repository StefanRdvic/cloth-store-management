package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Company")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Company {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="capital")
    private double capital;
    @Column(name="sales")
    private double sales;
    @Column(name="expenses")
    private double buys;

}
