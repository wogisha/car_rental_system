package cs544.edu.userMgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by eessc on 8/12/2017.
 */
@Controller
public class LoginController {
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/
    @RequestMapping("/login")
    public String login(Principal principal){
//        System.out.println(passwordEncoder.encode("test"));
        if(principal != null){
            return "redirect:/";
        }
        return "userMgmt/login";
    }

//    @ExceptionHandler(ForbiddenTargetException.class)
    @RequestMapping("/403")
    public String err(){
        return "403";
    }
}
