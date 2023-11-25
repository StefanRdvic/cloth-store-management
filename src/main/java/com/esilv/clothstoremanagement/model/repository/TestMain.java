package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Cloth;


// todo : DELETE
public class TestMain {
    public static void main(String[] args) {
        RepositoryFactory
                .getFactory()
                .getRepositoryFactory(Cloth.class)
                .save(Cloth
                        .builder()
                        .size(10)
                        .discount(0.4f)
                        .stock(100)
                        .resellPrice(100)
                        .retailPrice(90)
                        .name("test")
                        .build());


        RepositoryFactory
                .getFactory()
                .getRepositoryFactory(Cloth.class)
                .findAll()
                .forEach(c -> System.out.println(c.getName()+c.getSize()+c.getRetailPrice()+c.getId()));
    }
}
