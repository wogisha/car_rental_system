package cs544.edu.vehicles;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.entities.enums.VehicleType;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @RequestMapping({"", "/welcome"})
    public String welcome(Model model) {

        model.addAttribute("greeting", "Welcome to our Car Reservation System!!");
        model.addAttribute("tagline", "The most convienient way to get a car!");

        // return "redirect:/vehicles/add"; // to add Vehicle
        // return "vehicles/displayAllVehicles"; // Display All the Vehicles

        return "redirect:/vehicles/findVehicleByid"; // Find Vehicle By id
    }

    @GetMapping(value = "/update") // Update Vehicle Information
    public String UpdateVehicleInfo(HttpServletRequest request, Model model, @ModelAttribute Vehicle vehicle,
                                    BindingResult result) {

        Vehicle myvehicle = vehicleService.findByVehicleId(2);

        model.addAttribute("vehicle", myvehicle);

        model.addAttribute("fuels1", FuelType.values());
        model.addAttribute("vehiclestatus1", VehicleStatus.values());
        model.addAttribute("vehicletype1", VehicleType.values());

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/updateVehicle";
    }

    @PostMapping(value = "/update")
    public String UpdateVehicleInfoG(Model model, @Valid Vehicle vehicle, BindingResult result) {

        if (result.hasErrors()) {
            return "vehicles/updateVehicle";
        }

        model.addAttribute("vehicle", vehicle);

        vehicleService.save(vehicle);

        return "vehicles/VehicleUpdated";
    }

	/*
     * @RequestMapping(value = "/brandAndQuantityOfVehicles") // List of Vahicles
	 * with specific brand and seatQuantity public String
	 * ViewVehiclesSpecificBrandAndQuantity(HttpServletRequest request, Model
	 * model, @Valid Vehicle vehicle, BindingResult result) {
	 * 
	 * List<Vehicle> list = vehicleService.findByBrand("HONDA");
	 * model.addAttribute("brandlist", list);
	 * 
	 * if (result.hasErrors()) { return "error"; }
	 * 
	 * return "vehicles/brandAndQuantityVehicles"; }
	 */

    @RequestMapping(value = "/FuelTypePetrol") // List of Vahicles with Fuel Type as Petrol
    public String ViewVehiclesFuelTypePetrol(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                             BindingResult result) {

        List<Vehicle> list = vehicleService.findByfuelType(FuelType.PETROL);
        model.addAttribute("FuelTypePetrollist", list);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/FuelTypePetrolVehicles";
    }

    @RequestMapping(value = "/FuelTypeDiesel") // List of Vahicles with Fuel Type as Diesel
    public String ViewVehiclesFuelTypeDiesel(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                             BindingResult result) {

        List<Vehicle> list = vehicleService.findByfuelType(FuelType.DIESEL);
        model.addAttribute("FuelTypeDiesellist", list);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/FuelTypeDieselVehicles";
    }

    @RequestMapping(value = "/outOfService") // List of Out-Of-Service Vahicles
    public String ViewOutOfServiceVehicles(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                           BindingResult result) {

        List<Vehicle> list = vehicleService.findByStatus(VehicleStatus.OUT_OF_SERVICE);
        model.addAttribute("outServicelist", list);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/outOfServiceVehicles";
    }

    @RequestMapping(value = "/reserved") // List of Reserved Vahicles
    public String ViewReservedVehicles(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                       BindingResult result) {

        List<Vehicle> list = vehicleService.findByStatus(VehicleStatus.RESERVED);
        model.addAttribute("reservedlist", list);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/reservedVehicles";
    }

    @RequestMapping(value = "/rented") // List of Rented Vahicles
    public String ViewRentedVehicles(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                     BindingResult result) {

        List<Vehicle> list = vehicleService.findByStatus(VehicleStatus.RENTED);
        model.addAttribute("rentedlist", list);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/rentedVehicles";
    }

    @RequestMapping(value = "/available") // List of Available Vahicles
    public String ViewAvailableVehicles(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                        BindingResult result) {

        List<Vehicle> list = vehicleService.findByStatus(VehicleStatus.AVAILABLE);
        model.addAttribute("availablelist", list);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/availableVehicles";
    }

    @RequestMapping(value = "/deleteVehicleByid") // Delete Vehicle By id
    public String deleteVehicle(HttpServletRequest request, Model model, @Valid Vehicle vehicle, BindingResult result) {

        vehicleService.delete(3);

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/deleteVehicle";
    }

    @RequestMapping(value = "/displayAllVehicles1") // Find Vehicle By id
    public String getFindProductsForm(Model model, @Valid Vehicle vehicle, BindingResult result) {

        model.addAttribute("vehicleByid", vehicleService.findByVehicleId(2));

        if (result.hasErrors()) {
            return "error";
        }
        return "vehicles/findVehicle";
    }

    @RequestMapping(value = "/displayAllVehicles") // Display All the Vehicles
    public String getFindProductsForm(HttpServletRequest request, Model model, @Valid Vehicle vehicle,
                                      BindingResult result) {

        model.addAttribute("listofVehicles", vehicleService.getAllVehicles());

        return "vehicles/displayVehicles";
    }

    @GetMapping(value = "/add") // Add new Vehicle
    public String getAddNewProductForm(Model model, @ModelAttribute Vehicle vehicle,
                                       BindingResult result) {

        model.addAttribute("fuels", FuelType.values());
        model.addAttribute("vehiclestatus", VehicleStatus.values());
        model.addAttribute("vehicletype", VehicleType.values());

        return "vehicles/addVehicle";
    }

    @PostMapping(value = "/add")
    public String getAddNewProductFormG(Model model, @Valid Vehicle vehicle,
                                        BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("fuels", FuelType.values());
            model.addAttribute("vehiclestatus", VehicleStatus.values());
            model.addAttribute("vehicletype", VehicleType.values());
            return "vehicles/addVehicle";
        }


        vehicleService.save(vehicle);

        return "redirect:/vehicles/displayAllVehicles";
    }

    @RequestMapping(value = "/error", method = RequestMethod.POST) // To check the Error
    public String myError(HttpServletRequest request, Model model) {
        System.out.print("error");

        return "error";
    }
}
