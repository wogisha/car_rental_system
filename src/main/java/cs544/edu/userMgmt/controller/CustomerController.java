package cs544.edu.userMgmt.controller;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Employee;
import cs544.edu.entities.enums.UserRole;
import cs544.edu.userMgmt.service.CustomerService;
import cs544.edu.userMgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/newCust", method = RequestMethod.GET)
    public String createEmpForm(@ModelAttribute Employee employee, Model model) {

        return "userMgmt/newCustomer";
    }

    @RequestMapping(value = "/newCust", method = RequestMethod.POST)
    public String createEmp(@ModelAttribute @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userMgmt/newCustomer";
        } else {

            employee.setRole(UserRole.ROLE_CUSTOMER);
            employeeService.save(employee);

            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String createEmpForm(@ModelAttribute("customer") Customer customer, Principal principal, Model model) {
        Customer customer1 = customerService.findByUsername(principal.getName());
        if (customer1 != null) {
            model.addAttribute("customer", customer1);
        }

        return "userMgmt/regCustomer";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
            return "userMgmt/regCustomer";
        } else {

            Employee employee = employeeService.findByUsername(principal.getName());
            customer.setEmployee(employee);

            customerService.save(customer);

            return "redirect:/";
        }
    }


    @RequestMapping(value = "/viewCust", method = RequestMethod.GET)
    public String viewCustForm( Model model) {
        List<Customer> custList=customerService.getCustomerList();
        model.addAttribute("custList", custList);
        return "userMgmt/viewCustomer";
    }





}
