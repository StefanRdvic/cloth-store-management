package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Cloth;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClothRepository extends AbstractRepository<Cloth> {

    @Getter
    private final static ClothRepository repository = new ClothRepository();

    @Override
    protected SelectionQuery<Cloth> createSelectionQuery(Session s) {
        return s.createSelectionQuery("from Cloth", Cloth.class);
    }
}
