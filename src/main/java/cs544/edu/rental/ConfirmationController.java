package cs544.edu.rental;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cs544.edu.entities.Rent;
import cs544.edu.entities.Reservation;
import cs544.edu.reservations.ReservationService;

@Controller
@RequestMapping("/rental")
public class ConfirmationController {
	@Autowired
	private ReservationService reservationService;

	@GetMapping("confirmation")
	public String getConfirmation(Model model, @RequestParam("reservationId") Long reservationId,
			@ModelAttribute("rent") Rent rent, BindingResult bindingResult, HttpServletRequest request) {
		HttpSession session = request.getSession();

		Reservation reservation = reservationService.getById(reservationId);

		// rent.setDailyRentFee(reservation.getVehicle().getDailyPrice());

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
	public String createConfirmation(
			@Validated({ Rent.ValidateDate.class }) @ModelAttribute("rent")  Rent rent, BindingResult bindingResult,
			Model model, @RequestParam("reservationId") Long reservationId, HttpServletRequest request) {

		// @ModelAttribute("rent") @Validated({ Rent.ValidateDate.class }) Rent rent,
		// BindingResult bindingResult,
		HttpSession session = request.getSession();
		Rent rentSession = (Rent) session.getAttribute("rent");

		/*
		 * SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy"); String
		 * finalString = newFormat.format(rent.getRentDate());
		 */

		rentSession.setRentDate(rent.getRentDate());
		rentSession.setReturnDate(rent.getReturnDate());
		rent = rentSession;
		model.addAttribute("rent", rentSession);
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			model.addAttribute("error", "true");
			return "rental/Confirmation";
		} else {
			double totalPaid = rent.calculateCost(rent.getRentDate(), rent.getReturnDate(),
					rentSession.getReservation().getVehicle().getDailyPrice());
			rent.setTotalPaid(totalPaid);
			rentSession.setTotalPaid(totalPaid);
			rent = rentSession;
			model.addAttribute("rent", rentSession);
		}
		return "redirect:/rental/checkoutcar";
	}
}
