package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Shoe;

import java.util.Arrays;


// todo : DELETE
public class TestMain {
    public static void main(String[] args) {

        Arrays
                .asList("test1", "test2", "test3", "e", "fds", "ee", "sdfqsdfs", "afdsqffqs", "dsqdsqdq", "dsqsdqsq")
                .forEach(s -> RepositoryProvider
                        .provider()
                        .getRepository(Shoe.class)
                        .save(Shoe
                                .builder()
                                .size(10)
                                .discount(0.4f)
                                .stock(100)
                                .resellPrice(100)
                                .retailPrice(90)
                                .name(s)
                                .build()));
        RepositoryProvider
                .provider()
                .getRepository(Shoe.class)
                .findAll(10, 0)
                .forEach(c -> System.out.println(c.getName()+c.getSize()+c.getRetailPrice()+c.getId()));

        System.out.println(RepositoryProvider.provider().getRepository(Shoe.class).count());
    }
}
