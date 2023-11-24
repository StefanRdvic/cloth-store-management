package com.esilv.clothstoremanagement.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Enterprise")
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {
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
