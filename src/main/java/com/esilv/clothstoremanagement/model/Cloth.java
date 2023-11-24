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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Cloth")
@PrimaryKeyJoinColumn(name = "id")
public class Cloth extends Product{
    private int size;
}
