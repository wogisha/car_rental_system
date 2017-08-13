package cs544.edu.rental;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
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

	@RequestMapping("/checkincar")
	public String CheckInCar(Model model) {
		// ModelAndView mv = new ModelAndView ("CheckInCar");
		// mv.addObject("rent", new Rent());
		
		model.addAttribute("rent", new Rent());
		return "CheckInCar";
	}

	@PostMapping("/checkInCar")
	public String rentCar(@ModelAttribute("rent") Rent rent) {
		rentalService.saveRent(rent);
		return "checkincar";
	}
}
