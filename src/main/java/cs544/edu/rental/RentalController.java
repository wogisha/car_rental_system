package cs544.edu.rental;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cs544.edu.entities.Rent;
import cs544.edu.utilities.EmailService;

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
	public String getCustomersRentCar(Model model, @RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "page", defaultValue = "1") String page) {
		Page<Rent> rent = null;

		if (q != null && !q.isEmpty()) {
			rent = rentalService.getCustomerRentCarById(q.trim(), Integer.parseInt(page) - 1);
		} else {
			rent = rentalService.getAllCustomersRentCar(Integer.parseInt(page) - 1);
		}

		model.addAttribute("rent", rent);
		model.addAttribute("page", page);
		model.addAttribute("rentals", rentalService.getAll());

		return "rental/RentalList";
	}

	// @RequestMapping("/checkincar")
	// public ModelAndView checkInCar() {
	// ModelAndView mv = new ModelAndView("CheckInCar");
	// mv.addObject("rent", new Rent());
	// // model.addAttribute("rent", new Rent());
	// return mv;
	// }
	//
	// @PostMapping("/checkincar")
	// public String rentCar(@ModelAttribute("rent") Rent rent, BindingResult
	// bindingResult) {
	// if(bindingResult.hasErrors()) {
	// return "rental";
	// }
	// rentalService.saveRent(rent);
	// return "rental/CheckInCar";
	// }

	@GetMapping("/checkoutcar")
	public String getCheckoutCar(@ModelAttribute("rent") @Valid Rent rent, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		rent = (Rent) session.getAttribute("rent");
		model.addAttribute(rent);
		return "rental/CheckOutCar";
	}

	@PostMapping("/payment")
	public String saveCheckoutCar(HttpServletRequest request, @ModelAttribute("rent") @Valid Rent rent,
			BindingResult bindingResult, Model model, EmailService emailService) {
		HttpSession session = request.getSession();
		rent = (Rent) session.getAttribute("rent");
		model.addAttribute("rent", rent);
		if (bindingResult.hasErrors()) {
			return "rental/Confirmation";
		} else {
			rentalService.saveRent(rent);
			String custName = rent.getReservation().getCustomer().getFullName(); 
			emailService.sendSimpleMessage(custName, "Welcome" + custName,
					"Welcome to our car rental Service!"
					+ "Thank you for choosing our service! Have a nice and safe trip!");
			return "redirect:/rental";
		}
	}

	@GetMapping("{id}")
	public String viewRentalCarDetail(@PathVariable Long id, Model model) {
		Rent rent = rentalService.getOne(id);
		model.addAttribute("rent", rent);
		return "rental/RentalCarDetail";

	}

	@PostMapping("/updaterentalcar/{id}")
	public String updateRentalCarDetail(@PathVariable Long id, Model model) {

		Rent rent = rentalService.getOne(id);
		model.addAttribute("rent", rent);
		rentalService.returnedCar(rent);
		return "redirect:/rental";

	}
	@Scheduled(cron="")
	public void sendEmailToCustomerRentCar() {
		
	}
}
