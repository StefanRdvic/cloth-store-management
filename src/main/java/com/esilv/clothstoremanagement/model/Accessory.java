package com.esilv.clothstoremanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Accessory")
@PrimaryKeyJoinColumn(name = "id")
public class Accessory extends Product{
    @Id
    private int id;
}
