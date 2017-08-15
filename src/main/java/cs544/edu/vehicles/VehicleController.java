package cs544.edu.vehicles;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.entities.enums.VehicleType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @RequestMapping({"", "/welcome"})
    public String welcome(Model model) {
        model.addAttribute("listofVehicles", vehicleService.getAllVehicles());
        return "vehicles/displayVehicles";
    }

    @GetMapping(value = "/update/{id}") // Update Vehicle Information
    public String UpdateVehicleInfo(@PathVariable Long id, Model model, @ModelAttribute Vehicle vehicle,
                                    BindingResult result) {

        Vehicle myvehicle = vehicleService.findByVehicleId(id);


        model.addAttribute("vehicle", myvehicle);

        model.addAttribute("fuels1", FuelType.values());
        model.addAttribute("vehiclestatus1", VehicleStatus.values());
        model.addAttribute("vehicletype1", VehicleType.values());

        if (result.hasErrors()) {
            return "error";
        }

        return "vehicles/updateVehicle";
    }

    @PostMapping(value = "/update/{id}")
    public String UpdateVehicleInfoG(@PathVariable Long id,Model model, @Valid Vehicle vehicle, BindingResult result,RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "vehicles/updateVehicle";
        }

        model.addAttribute("vehicle", vehicle);

        vehicleService.save(vehicle);

        redirectAttributes.addFlashAttribute("updatedVehicleMessage", "updated vehicle information successfully");
        return "redirect:/vehicles";
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

    @RequestMapping(value = "/delete/{id}") // Delete Vehicle By id
    public String deleteVehicle(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            vehicleService.delete(id);
            redirectAttributes.addFlashAttribute("updatedVehicleMessage", "vehicle deleted successfully");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("updatedVehicleMessage", "sorry cannot delete vehicle becoz it has records associated with it  ");
        }
        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/view/{id}") // Find Vehicle By id
    public String getFindProductsForm(Model model, @PathVariable("id")  Long id) {

        model.addAttribute("vehicleByid", vehicleService.findByVehicleId(id));

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
