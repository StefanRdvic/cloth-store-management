package com.esilv.clothstoremanagement.model.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryProvider {

    @Getter
    @Accessors(fluent = true)
    private static final RepositoryProvider provider = new RepositoryProvider();

    @SuppressWarnings("unchecked")
    public <T> CrudRepository<T> getRepository(Class<T> clazz){
        return (CrudRepository<T>) switch (clazz.getSimpleName()){
            case "Company" -> CompanyRepository.repository();
            case "Cloth" -> ClothRepository.repository();
            case "Accessory" -> AccessoryRepository.repository();
            case "Shoe" -> ShoeRepository.repository();
            default -> throw new IllegalStateException("Unexpected value: " + clazz.getSimpleName());
        };
    }
}
