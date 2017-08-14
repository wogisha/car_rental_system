package cs544.edu.userMgmt.service.impl;

import cs544.edu.entities.Employee;
import cs544.edu.userMgmt.repository.EmployeeRepository;
import cs544.edu.userMgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eessc on 8/12/2017.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(Employee employee){
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }
    public List<Employee> getEmployeeList(){
     return  ( List<Employee>) employeeRepository.findAll();

    }
    public Employee findOne(Long id){
        return employeeRepository.findOne(id);
    }
    public Employee findByUsername(String username){
        return employeeRepository.findByUsername(username);
    }
}
