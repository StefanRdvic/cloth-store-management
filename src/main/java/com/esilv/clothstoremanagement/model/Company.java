package com.esilv.clothstoremanagement.model;

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
    @Column(name="sells")
    private double sells;
    @Column(name="buys")
    private double buys;

}
