package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Cloth;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClothRepository extends AbstractRepository<Cloth> {

    @Getter(lazy = true)
    @Accessors(fluent = true)
    private final static ClothRepository repository = new ClothRepository();

    @Override
    protected SelectionQuery<Cloth> createSelectionQuery(Session session) {
        return session
                .createSelectionQuery("from Cloth", Cloth.class);
    }

    @Override
    protected SelectionQuery<Cloth> createSelectionQuery(Session session, String searchValue) {
        return session
                .createSelectionQuery(
                        "from Cloth where name like :searchValue",
                        Cloth.class)
                .setParameter("searchValue", "%" + searchValue + "%");
    }

    @Override
    protected Query<Cloth> createCountQuery(Session session) {
        return session.createQuery("select count(*) from Cloth", Cloth.class);
    }

    @Override
    protected Query<Cloth> createCountQuery(Session session, String searchValue) {
        return session
                .createQuery(
                        "select count(*) from Cloth where name like :searchValue",
                        Cloth.class)
                .setParameter("searchValue", "%" + searchValue + "%");
    }
}
