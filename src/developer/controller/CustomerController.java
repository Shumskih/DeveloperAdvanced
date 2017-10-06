package developer.controller;

import developer.dao.JavaIOCustomerDAOImpl;
import developer.model.Customer;
import developer.model.Developer;

public class CustomerController {
    private JavaIOCustomerDAOImpl javaIOCustomerDAOImpl = new JavaIOCustomerDAOImpl();

    public void save(Customer customer) {
        javaIOCustomerDAOImpl.save(customer);
    }

    public void update(Customer customer) {
        javaIOCustomerDAOImpl.update(customer);
    }

    public Developer getById(int id) {
        javaIOCustomerDAOImpl.getById(id);
        return null;
    }

    public void showAllCustomers() {
        javaIOCustomerDAOImpl.showAllCustomers();
    }
}
