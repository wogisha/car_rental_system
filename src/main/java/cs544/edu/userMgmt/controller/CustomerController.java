package cs544.edu.userMgmt.controller;

import cs544.edu.entities.Customer;
import cs544.edu.userMgmt.repository.CustomerRepository;
import cs544.edu.userMgmt.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/newCust", method = RequestMethod.GET)
    public String createEmpForm(@ModelAttribute("customer") Customer customer) {
        return "userMgmt/regCustomer";
    }

    @RequestMapping(value = "/newCust", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userMgmt/regCustomer";
        } else {
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
