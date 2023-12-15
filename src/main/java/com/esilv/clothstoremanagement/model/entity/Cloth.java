package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * This class represents a cloth
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Entity
@Table(name="Cloth")
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Cloth extends Product{
    @Column(name = "size")
    private String size;
}
