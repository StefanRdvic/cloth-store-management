package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name="Accessory")
@PrimaryKeyJoinColumn(name = "id")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Accessory extends Product{
}
