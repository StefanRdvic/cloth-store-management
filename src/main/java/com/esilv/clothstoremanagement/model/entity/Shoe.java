package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * This class represents a shoe
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Entity
@Table(name="Shoe")
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Shoe extends Product{
    @Column(name = "size")
    private double size;
}
