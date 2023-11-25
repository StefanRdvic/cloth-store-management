package com.esilv.clothstoremanagement.model.repository;

import com.esilv.clothstoremanagement.model.entity.Company;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyRepository extends AbstractRepository<Company> {

    @Getter
    private static final CompanyRepository repository = new CompanyRepository();

    @Override
    protected SelectionQuery<Company> createSelectionQuery(Session s) {
        return s.createSelectionQuery("from Company", Company.class);
    }
}
