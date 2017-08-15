package cs544.edu.userMgmt.service;

import cs544.edu.entities.Employee;

import java.util.List;

/**
 * Created by eessc on 8/12/2017.
 */
public interface EmployeeService {
    public void save(Employee employee);
    public List<Employee> getEmployeeList();
    public Employee findOne(Long id);
    public Employee findByUsername(String username);
}
