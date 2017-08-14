package cs544.edu.rental;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Rent;
import cs544.edu.entities.Vehicle;

@Controller
@RequestMapping("/rental")
public class ConfirmationController {
	
	
	
	@GetMapping("confirmation")
	public String getConfirmation(@ModelAttribute("rent") Rent rent, HttpServletRequest request, Model model){
		HttpSession session=request.getSession();
		
		Customer customer = new Customer();
		customer.setFullName("Ha Thuy Hong");
		customer.setLicenseNumber("123456789");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("brand");
		vehicle.setModel("model");
		
		rent.setDailyRentFee(10);
		rent.setCustomer(customer);

		Date date = new Date();		
		rent.setRentDate(date);
		rent.setReturnDate(date);
		
		//rent.getReservation().setVehicle(vehicle);
		session.setAttribute("rent", rent);
		model.addAttribute(rent);
		
		return "Confirmation";
	}
	
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder) {
		// Custom Date Editor
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
	}
	
	@PostMapping("confirmation")
	public String createConfirmation(@ModelAttribute("rent") Rent rent, HttpServletRequest request){	
		HttpSession session=request.getSession();
		Rent rentSession = (Rent)session.getAttribute("rent");
		//rent.setTotalPaid(rent.calculateCost(rent.getRentDate(), rent.getReturnDate(), rent.getReservation().getVehicle().getDailyPrice()));		
		
		double totalPaid = rent.calculateCost(rent.getRentDate(), rent.getReturnDate(), 20.00);
		rent.setTotalPaid(totalPaid);
		rentSession.setTotalPaid(totalPaid);

		/*SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
		String finalString = newFormat.format(rent.getRentDate());*/
		
		rentSession.setRentDate(rent.getRentDate());
		rentSession.setReturnDate(rent.getReturnDate());
		
		return "redirect:/rental/checkoutcar";
	}
}
