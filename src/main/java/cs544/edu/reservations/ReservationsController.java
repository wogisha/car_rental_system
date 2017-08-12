package cs544.edu.reservations;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationsController {


    @GetMapping("")
    public String getReservations(){
        return "reservations/list";
    }
}
