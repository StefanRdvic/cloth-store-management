package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Company;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;


/**
 * This class represents a company repository
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyRepository extends AbstractRepository<Company> {

    @Getter(lazy = true)
    @Accessors(fluent = true)
    private static final CompanyRepository repository = new CompanyRepository();

    @Override
    protected SelectionQuery<Company> createSelectionQuery(Session session) {
        return session.createSelectionQuery("from Company", Company.class);
    }

    @Override
    protected SelectionQuery<Company> createSelectionQuery(Session session, String searchValue) {
        throw new RuntimeException("no selection for Company");
    }

    @Override
    protected Query<Company> createCountQuery(Session session) {
        throw new RuntimeException("no count for Company");
    }

    @Override
    protected Query<Company> createCountQuery(Session session, String searchValue) {
        throw new RuntimeException("no count for Company");
    }
}
