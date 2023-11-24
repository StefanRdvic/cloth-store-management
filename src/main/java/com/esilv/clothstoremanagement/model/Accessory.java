package com.esilv.clothstoremanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Accessory")
@PrimaryKeyJoinColumn(name = "id")
public class Accessory extends Product{
    @Id
    private int id;
}
