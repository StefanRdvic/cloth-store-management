package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Accessory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;


@NoArgsConstructor()
public class AccessoryRepository extends AbstractRepository<Accessory> {

    @Getter
    @Accessors(fluent = true)
    private final static AccessoryRepository repository = new AccessoryRepository();


    @Override
    protected SelectionQuery<Accessory> createSelectionQuery(Session session) {
        return session
                .createSelectionQuery("from Accessory", Accessory.class);
    }

    @Override
    protected SelectionQuery<Accessory> createSelectionQuery(Session session, String searchValue) {
        return session
                .createSelectionQuery(
                        "from Accessory where name like :searchValue",
                        Accessory.class)
                .setParameter("searchValue", "%" + searchValue + "%");
    }

    @Override
    protected Query<Accessory> createCountQuery(Session session) {
        return session.createQuery("select count(*) from Accessory", Accessory.class);
    }

    @Override
    protected Query<Accessory> createCountQuery(Session session, String searchValue) {
        return session
                .createQuery(
                        "select count(*) from Accessory where name like :searchValue",
                        Accessory.class)
                .setParameter("searchValue", "%" + searchValue + "%");
    }
}
