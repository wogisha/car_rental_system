package cs544.edu.userMgmt.service;

import cs544.edu.entities.Customer;

import java.util.List;

public interface CustomerService {

    public void save(Customer customer);
    public List<Customer> getCustomerList();
    public Customer findOne(Long id);
    public Customer findByID(Long ID);

    public Customer findByUsername(String username);
}
