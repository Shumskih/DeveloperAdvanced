package developer.controller;

import developer.dao.JavaIOCompanyDAOImpl;
import developer.model.Company;
import developer.model.Developer;

public class CompanyController {
    private JavaIOCompanyDAOImpl javaIOCompanyDAOImpl = new JavaIOCompanyDAOImpl();

    public void save(Company company) {
        javaIOCompanyDAOImpl.save(company);
    }

    public void update(Company company) {
        javaIOCompanyDAOImpl.update(company);
    }

    public Developer getById(int id) {
        javaIOCompanyDAOImpl.getById(id);
        return null;
    }

    public void showAllCompanies() {
        javaIOCompanyDAOImpl.showAllCompanies();
    }
}
