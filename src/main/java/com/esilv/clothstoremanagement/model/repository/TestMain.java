package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Company;


// todo : DELETE
public class TestMain {
    public static void main(String[] args) {
        RepositoryProvider.provider().getRepository(Company.class)
                .save(Company.builder()
                        .globalIncome(0)
                        .capital(3000000)
                        .globalCost(0)
                        .build());

        Company e = RepositoryProvider.provider().getRepository(Company.class).findFirst();
        System.out.println(e.getCapital());
    }
}
