package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * This class represents an accessory
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Entity
@Table(name="Accessory")
@PrimaryKeyJoinColumn(name = "id")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Accessory extends Product{
}
