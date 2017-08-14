package cs544.edu.userMgmt.service.impl;

import cs544.edu.entities.Customer;
import cs544.edu.userMgmt.repository.CustomerRepository;
import cs544.edu.userMgmt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl  implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;



    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
       return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer findOne(Long id) {
       return  customerRepository.findOne(id);
    }

    @Override
    public Customer findByID(Long ID) {
        return null;
    }
}
