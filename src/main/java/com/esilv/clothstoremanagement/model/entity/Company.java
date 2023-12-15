package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents a company
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Entity
@Table(name = "Company")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class Company {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="capital")
    private double capital;
    @Column(name="global_income")
    private double globalIncome;
    @Column(name="global_cost")
    private double globalCost;
}
