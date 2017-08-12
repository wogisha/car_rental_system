package cs544.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wogisha on 12/08/2017.
 */
@Controller
public class MainController {
    @RequestMapping
    public String mainPage(){
        return "redirect:/vechiles";
    }
}
