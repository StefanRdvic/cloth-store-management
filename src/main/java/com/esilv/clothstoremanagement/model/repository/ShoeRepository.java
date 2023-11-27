package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Shoe;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoeRepository extends AbstractRepository<Shoe> {

    @Getter
    @Accessors(fluent = true)
    private final static ShoeRepository repository = new ShoeRepository();

    @Override
    protected SelectionQuery<Shoe> createSelectionQuery(Session session) {
        return session.createSelectionQuery("from Shoe", Shoe.class);
    }

    @Override
    protected SelectionQuery<Shoe> createSelectionQuery(Session session, String searchValue) {
        return session
                .createSelectionQuery(
                        "from Shoe where name like :searchValue",
                        Shoe.class)
                .setParameter("searchValue", "%" + searchValue + "%");
    }

    @Override
    protected Query<Shoe> createCountQuery(Session session) {
        return session.createQuery("select count(*) from Shoe", Shoe.class);
    }

    @Override
    protected Query<Shoe> createCountQuery(Session session, String searchValue) {
        return session
                .createQuery(
                        "select count(*) from Shoe where name like :searchValue",
                        Shoe.class)
                .setParameter("searchValue", "%" + searchValue + "%");    }
}
