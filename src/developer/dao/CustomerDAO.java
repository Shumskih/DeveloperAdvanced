package developer.dao;

import developer.model.Customer;

public interface CustomerDAO {
    void save(Customer customer);

    void update(Customer customer);

    void getById(Integer id);

    void showAllCustomers();
}
