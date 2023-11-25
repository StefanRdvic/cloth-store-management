package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Shoe;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoeRepository extends AbstractRepository<Shoe> {

    @Getter
    private final static ShoeRepository repository = new ShoeRepository();

    @Override
    protected SelectionQuery<Shoe> createSelectionQuery(Session s) {
        return s.createSelectionQuery("from Shoe", Shoe.class);
    }
}
