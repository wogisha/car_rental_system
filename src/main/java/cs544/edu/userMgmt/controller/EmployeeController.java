package cs544.edu.userMgmt.controller;

import cs544.edu.entities.Employee;
import cs544.edu.entities.enums.UserRole;
import cs544.edu.userMgmt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eessc on 8/12/2017.
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createEmpForm(@ModelAttribute Employee employee, Model model) {
        Map<String, String> roleList = new HashMap<>();
        for (UserRole userType : UserRole.values()) {
            roleList.put(userType.name(), userType.name());
        }
        model.addAttribute("roleList", roleList);
        return "userMgmt/addEmp";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createEmp(@ModelAttribute @Valid Employee employee, BindingResult result,Model model) {
        if (result.hasErrors()) {
            Map<String, String> roleList = new HashMap<>();
            for (UserRole userType : UserRole.values()) {
                roleList.put(userType.name(), userType.name());
            }
            model.addAttribute("roleList", roleList);
            return "userMgmt/addEmp";
        } else {
            employeeService.save(employee);
            return "redirect:/emp/viewEmp";
        }
    }


    @RequestMapping(value = "/viewEmp", method = RequestMethod.GET)
    public String viewEmpForm( Model model) {
        List<Employee> empList=employeeService.getEmployeeList();
        model.addAttribute("empList", empList);
        return "userMgmt/viewEmp";
    }
    @PreAuthorize("hasRole('MANAGER')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateEmpF(@PathVariable("id") Long id,Model model){
        Map<String, String> roleList = new HashMap<>();
        for (UserRole userType : UserRole.values()) {
            roleList.put(userType.name(), userType.name());
        }
        model.addAttribute("roleList", roleList);
        Employee employee = employeeService.findOne(id);
        model.addAttribute("employee",employee);
        model.addAttribute("update",true);
        return "userMgmt/addEmp";
    }

    @RequestMapping(value = "changePw", method = RequestMethod.POST)
    public String changePw(String olPpassword, String newPassword1, String newPassword2, Principal principal,Model model){
        if(!newPassword1.equals(newPassword2)){
            model.addAttribute("error","New Passwords did not match");
            return "userMgmt/changePasswordn";
        }
        Employee emp = employeeService.findByUsername(principal.getName());
        if(passwordEncoder.matches(olPpassword,emp.getPassword())){
            emp.setPassword(passwordEncoder.encode(newPassword1));
        }
        employeeService.save(emp);
        return "redirect:/";
    }

    @RequestMapping(value = "changePw", method = RequestMethod.GET)
    public String changePwF(){
        return "userMgmt/changePassword";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateEmp(@PathVariable("id") Long id, Employee employee){
        Employee employee1 = employeeService.findOne(id);
        employee.setId(employee1.getId());
        employee.setPassword(employee1.getPassword());
        employeeService.save(employee);
        return "redirect:/emp/viewEmp";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @RequestMapping(value = "/deactivate/{id}", method = RequestMethod.GET)
    public String deactivateEmp(@PathVariable("id") Long id){
        Employee employee1 = employeeService.findOne(id);
        employee1.setEnabled(false);
        employeeService.save(employee1);
        return "redirect:/emp/viewEmp";
    }




}
