package com.esilv.clothstoremanagement.model;

import com.esilv.clothstoremanagement.model.entity.Accessory;
import com.esilv.clothstoremanagement.model.entity.Cloth;
import com.esilv.clothstoremanagement.model.entity.Company;
import com.esilv.clothstoremanagement.model.entity.Shoe;
import com.esilv.clothstoremanagement.model.repository.RepositoryProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * This class is an util for populating the database
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SetupScenario {

    public static void main(String[] args) {
        populateForDemo();
//        populateDB();
    }

    private static void populateForDemo(){
        RepositoryProvider provider = RepositoryProvider.provider();

        provider.getRepository(Company.class)
                .save(Company.builder()
                        .globalIncome(0)
                        .capital(20000)
                        .globalCost(0)
                        .build()
                );

        Arrays.asList(
                Cloth.builder()
                        .name("Dress1")
                        .stock(10)
                        .resellPrice(30)
                        .retailPrice(30)
                        .size("M")
                        .discount(0.0)
                        .build(),
                Cloth.builder()
                        .name("Dress2")
                        .stock(20)
                        .resellPrice(40)
                        .retailPrice(40)
                        .size("M")
                        .discount(0.0)
                        .build()

        ).forEach(provider.getRepository(Cloth.class)::save);

        Random random = new Random();

        IntStream.rangeClosed(1, 60)
                .mapToObj(i -> Shoe.builder()
                        .name("Shoe" + i)
                        .stock(random.nextInt(50))
                        .resellPrice(random.nextInt(300) + 1)
                        .retailPrice(random.nextInt(300) + 1)
                        .size(random.nextInt(50) + 1)
                        .discount(Math.round(random.nextDouble() * 10) / 10.0)
                        .build())
                .forEach(provider.getRepository(Shoe.class)::save);

        IntStream.rangeClosed(1, 60)
                .mapToObj(i -> Accessory.builder()
                        .name("Accessory" + i)
                        .stock(random.nextInt(50))
                        .resellPrice(random.nextInt(300) + 1)
                        .retailPrice(random.nextInt(300) + 1)
                        .discount(Math.round(random.nextDouble() * 10) / 10.0)
                        .build())
                .forEach(provider.getRepository(Accessory.class)::save);

    }


    private static void populateDB(){
        RepositoryProvider provider = RepositoryProvider.provider();

        provider.getRepository(Company.class)
                .save(Company.builder()
                        .globalIncome(0)
                        .capital(20000)
                        .globalCost(0)
                        .build());

        Random random = new Random();
        String[] sizes = {"XS", "S", "M", "L", "XL"};

        IntStream.rangeClosed(1, 60)
                .mapToObj(i -> Cloth.builder()
                        .name("Cloth" + i)
                        .stock(random.nextInt(50))
                        .resellPrice(random.nextInt(300) + 1)
                        .retailPrice(random.nextInt(300) + 1)
                        .size(sizes[random.nextInt(sizes.length)])
                        .discount(Math.round(random.nextDouble() * 10) / 10.0)
                        .build())
                .forEach(provider.getRepository(Cloth.class)::save);


        IntStream.rangeClosed(1, 60)
                .mapToObj(i -> Shoe.builder()
                        .name("Shoe" + i)
                        .stock(random.nextInt(50))
                        .resellPrice(random.nextInt(300) + 1)
                        .retailPrice(random.nextInt(300) + 1)
                        .size(random.nextInt(50) + 1)
                        .discount(Math.round(random.nextDouble() * 10) / 10.0)
                        .build())
                .forEach(provider.getRepository(Shoe.class)::save);

        IntStream.rangeClosed(1, 60)
                .mapToObj(i -> Accessory.builder()
                        .name("Shoe" + i)
                        .stock(random.nextInt(50))
                        .resellPrice(random.nextInt(300) + 1)
                        .retailPrice(random.nextInt(300) + 1)
                        .discount(Math.round(random.nextDouble() * 10) / 10.0)
                        .build())
                .forEach(provider.getRepository(Accessory.class)::save);
    }
}
