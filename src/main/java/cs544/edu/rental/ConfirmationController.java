package cs544.edu.rental;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cs544.edu.entities.Reservation;
import cs544.edu.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import cs544.edu.entities.Rent;

@Controller
@RequestMapping("/rental")
public class ConfirmationController {
	@Autowired
	private ReservationService reservationService;
	
	
	
	@GetMapping("confirmation")
	public String getConfirmation(@ModelAttribute("rent") Rent rent, HttpServletRequest request, Model model,
								  @RequestParam("reservationId") Long reservationId){
		HttpSession session=request.getSession();

		Reservation reservation = reservationService.getById(reservationId);

//		rent.setDailyRentFee(reservation.getVehicle().getDailyPrice());

		rent.setCustomer(reservation.getCustomer());
		rent.setVehicle(reservation.getVehicle());

		rent.setRentDate(reservation.getPickupDate());
		rent.setReturnDate(reservation.getReturnDate());
		
		rent.setReservation(reservation);
		session.setAttribute("rent", rent);
		model.addAttribute(rent);
		
		return "rental/Confirmation";
	}
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		// Custom Date Editor
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
	}
	
	@PostMapping("confirmation")
	public String createConfirmation(@ModelAttribute("rent") Rent rent, HttpServletRequest request, Model model){	
		HttpSession session=request.getSession();
		Rent rentSession = (Rent)session.getAttribute("rent");
		//rent.setTotalPaid(rent.calculateCost(rent.getRentDate(), rent.getReturnDate(), rent.getReservation().getVehicle().getDailyPrice()));		
		
		double totalPaid = rent.calculateCost(rent.getRentDate(), rent.getReturnDate(), rentSession.getReservation().getVehicle().getDailyPrice());
		rent.setTotalPaid(totalPaid);
		rentSession.setTotalPaid(totalPaid);

		/*SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
		String finalString = newFormat.format(rent.getRentDate());*/
		
		rentSession.setRentDate(rent.getRentDate());
		rentSession.setReturnDate(rent.getReturnDate());
		
		model.addAttribute(rentSession);
		return "redirect:/rental/checkoutcar";
	}
}
