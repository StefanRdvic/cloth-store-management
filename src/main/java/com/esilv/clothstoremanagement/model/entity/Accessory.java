package com.esilv.clothstoremanagement.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name="Accessory")
@PrimaryKeyJoinColumn(name = "id")
public class Accessory extends Product{
}
