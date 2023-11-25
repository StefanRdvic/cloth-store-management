package com.esilv.clothstoremanagement.model.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryFactory {

    @Getter
    private static final RepositoryFactory factory = new RepositoryFactory();

    @SuppressWarnings("unchecked")
    public <T> CrudRepository<T> getRepositoryFactory(Class<T> clazz){
        return (CrudRepository<T>) switch (clazz.getSimpleName()){
            case "Company" -> CompanyRepository.getRepository();
            case "Cloth" -> ClothRepository.getRepository();
            case "Accessory" -> AccessoryRepository.getRepository();
            case "Shoe" -> ShoeRepository.getRepository();
            default -> throw new IllegalStateException("Unexpected value: " + clazz.getSimpleName());
        };
    }
}
