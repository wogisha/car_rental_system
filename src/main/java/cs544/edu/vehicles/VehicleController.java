package cs544.edu.vehicles;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import cs544.edu.entities.Vehicle;   
import cs544.edu.vehicles.services.VehicleService;


@Controller
@RequestMapping("/vehicles")
public class VehicleController {

//	@Autowired
//	private VehicleService vehicleService;
//	@Autowired
//	private VehicleTypeService vehicleTypeService;
	
	@RequestMapping({ "/", "/welcome" })
	public String welcome(Model model) {

		model.addAttribute("greeting", "Welcome to our Car Reservation System!!");
		model.addAttribute("tagline", "The most convienient way to get a car!");
		return "vehicles/addVehicle";
	}


	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute("newVehicle", vehicle);
//		model.addAttribute("types", vehicleTypeService.findAll());
		return "addVehicle";

	}
	
	
/*	@RequestMapping({ "", "/all" })
	public String list(Model model) {
		// setRole(session, model);
		model.addAttribute("vehicles", vehicleService.getAllVehicles());
		return "vehicles";
	}  */

/*	@RequestMapping({ "/list" })
	public String listForCustomer(Model model) {
		// setRole(session, model);
		model.addAttribute("vehicles", vehicleService.getAllVehicles());
		return "vehiclesCustomer";
	}  */

/*	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("newVehicle") @Valid Vehicle vehicle, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "addVehicle";
		}

		vehicleService.save(vehicle);

		return "redirect:/vehicles";
	}  */

/*	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public String vehicleDetail(@RequestParam("id") Long vehicleId, Model model) {
		Vehicle vehicle = vehicleService.findByVehicleId(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return "vehicle";
	}  */

}
