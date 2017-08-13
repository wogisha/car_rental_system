package cs544.edu.reservations;


import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public String getReservations(Model model, @RequestParam(value = "q", required = false) String q, @RequestParam(value = "page", defaultValue = "1") String page) {
        Page<Reservation> reservationList = null;

        if (q != null && !q.isEmpty()) {
            reservationList = reservationService.getCustomerReservationsById(q.trim(), Integer.parseInt(page) - 1);
        } else {
            reservationList = reservationService.getAllReservations(Integer.parseInt(page) - 1);
        }

        model.addAttribute("reservations", reservationList);
        model.addAttribute("page", page);

        return "reservations/list";
    }

    @GetMapping("add")
    public String makeReservation(Model model, @RequestParam(value = "vehicleId", required = false) Long vehicleId,
                                  @ModelAttribute Reservation reservation) {
        Vehicle vehicleToReserve = null;
        try {
            vehicleToReserve = reservationService.getVehicleToReserve(vehicleId);
        } catch (CannotReserveVehicleException ex) {
            ex.printStackTrace();
            return "redirect:/";
        }

        model.addAttribute("vehicle", vehicleToReserve);
        return "reservations/add";
    }

    @PostMapping("add")
    public String saveReservation(Model model, @RequestParam(value = "vehicleId", required = false) Long vehicleId,
                                  RedirectAttributes redirectAttributes,
                                  @Validated({Reservation.NewReservation.class}) Reservation reservation,
                                  BindingResult bindingResult) {

        Vehicle vehicleToReserve = null;
        try {
            vehicleToReserve = reservationService.getVehicleToReserve(vehicleId);
        } catch (CannotReserveVehicleException ex) {
            ex.printStackTrace();
            return "redirect:/vehicles";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("vehicle", vehicleToReserve);
            return "reservations/add";
        }

        reservationService.makeReservation(reservation, 1L, vehicleToReserve);

        redirectAttributes.addFlashAttribute("reservation_message", "made reservation successfully");

        return "redirect:/reservations";
    }

    @GetMapping("{id}")
    public String viewReservation(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getById(id);

        model.addAttribute("reservation", reservation);

        return "reservations/view";

    }


    @PostMapping("{id}")
    public String updateReservation(@PathVariable Long id, Model model) {

        return "redirect:/reservations/" + id;
    }

    @GetMapping("{id}/cancel")
    public String cancelReservation(@PathVariable Long id, Model model) {

        reservationService.cancelReservation(id);

        return "redirect:/reservations";
    }
}
