package cs544.edu.rental;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cs544.edu.entities.Rent;

@Controller
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder) {
        // Custom Date Editor
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }

    @RequestMapping("")
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAll());
        return "rental/RentalList";
    }

    @RequestMapping("/checkincar")
    public ModelAndView checkInCar() {
        ModelAndView mv = new ModelAndView("CheckInCar");
        mv.addObject("rent", new Rent());
        //model.addAttribute("rent", new Rent());
        return mv;
    }

    @PostMapping("/checkincar")
    public String rentCar(@ModelAttribute("rent") Rent rent) {
        rentalService.saveRent(rent);
        return "rental/CheckInCar";
    }

    ///////////Test
    @GetMapping("/checkoutcar")
    public String getCheckoutCar(@ModelAttribute("rent") Rent rent, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Rent rentSession = (Rent) session.getAttribute("rent");

        model.addAttribute(rentSession);
        return "rental/CheckOutCar";
    }

    @PostMapping("/payment")
    public String saveCheckoutCar(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Rent rent = (Rent) session.getAttribute("rent");
        rentalService.saveRent(rent);
        //note ve home
        return "redirect:/rental";
    }
}
