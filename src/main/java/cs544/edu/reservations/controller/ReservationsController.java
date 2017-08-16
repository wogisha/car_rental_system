package cs544.edu.reservations.controller;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.reservations.exceptions.CannotReserveVehicleException;
import cs544.edu.reservations.services.ReservationService;
import cs544.edu.userMgmt.service.CustomerService;

@Controller
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String getReservations(Model model, @RequestParam(value = "q", required = false) String q, @RequestParam(value = "page", defaultValue = "1") String page, HttpServletRequest httpServletRequest,Principal principal) {
        Page<Reservation> reservationList = null;

        if (httpServletRequest.isUserInRole("ROLE_CUSTOMER")) {
            Customer customer = customerService.findByUsername(principal.getName());
            if (customer != null) {
                reservationList = reservationService.getCustomerReservations(customer.getId(), Integer.parseInt(page) - 1);
            }

        } else {


            if (q != null && !q.isEmpty()) {
                reservationList = reservationService.getCustomerReservationsById(q.trim(), Integer.parseInt(page) - 1);
            } else {
                reservationList = reservationService.getAllReservations(Integer.parseInt(page) - 1);
            }
        }

        model.addAttribute("reservations", reservationList);
        model.addAttribute("page", page);

        return "reservations/list";
    }

    @GetMapping("add")
    public String makeReservation(Model model, @RequestParam(value = "vehicleId", required = false) Long vehicleId,
                                  @ModelAttribute Reservation reservation, HttpServletRequest httpServletRequest, Principal principal) {
        Vehicle vehicleToReserve = null;

        try {
            vehicleToReserve = reservationService.getVehicleToReserve(vehicleId);
        } catch (CannotReserveVehicleException ex) {
            ex.printStackTrace();
            return "redirect:/";
        }

        model.addAttribute("vehicle", vehicleToReserve);

        if (httpServletRequest.isUserInRole("ROLE_CUSTOMER")) {
            System.out.println("this is a customer");
            model.addAttribute("customer", customerService.findByUsername(principal.getName()));
        } else {
            model.addAttribute("customers", reservationService.getCustomers());
        }


        return "reservations/add";
    }

    @PostMapping("add")
    public String saveReservation(Model model, @RequestParam(value = "vehicleId", required = false) Long vehicleId,
                                  RedirectAttributes redirectAttributes,
                                  @Validated({Reservation.NewReservation.class}) Reservation reservation,

                                  BindingResult bindingResult, @RequestParam(value = "customerId") Long customerId, HttpServletRequest httpServletRequest, Principal principal) {

        Vehicle vehicleToReserve = null;

        try {
            vehicleToReserve = reservationService.getVehicleToReserve(vehicleId);
        } catch (CannotReserveVehicleException ex) {
            ex.printStackTrace();
            return "redirect:/vehicles";
        }

        if (httpServletRequest.isUserInRole("ROLE_CUSTOMER")) {

            Customer customer = customerService.findByUsername(principal.getName());
            if (customer == null) {
                redirectAttributes.addFlashAttribute("customerProfileMessage", "Please enter your profile details before you can reserve a car");
                return "redirect:/cust/profile";
            }

            model.addAttribute("customer", customer);
        } else {
            model.addAttribute("customers", reservationService.getCustomers());
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("vehicle", vehicleToReserve);
            model.addAttribute("customerId", customerId);


            return "reservations/add";
        }

        reservationService.makeReservation(reservation, customerId, vehicleToReserve);

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
