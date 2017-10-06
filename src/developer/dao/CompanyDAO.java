package developer.dao;

import developer.model.Company;

public interface CompanyDAO {
    void save(Company company);

    void update(Company company);

    void getById(Integer id);

    void showAllCompanies();
}

