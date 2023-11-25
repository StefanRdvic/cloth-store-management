package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Accessory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;


@NoArgsConstructor()
public class AccessoryRepository extends AbstractRepository<Accessory> {

    @Getter
    private final static AccessoryRepository repository = new AccessoryRepository();


    @Override
    protected SelectionQuery<Accessory> createSelectionQuery(Session s) {
        return s.createSelectionQuery("from Accessory", Accessory.class);
    }
}
