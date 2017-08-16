package cs544.edu.reservations.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.reservations.exceptions.CannotReserveVehicleException;
import cs544.edu.reservations.repositories.ReservationRepository;
import cs544.edu.userMgmt.repository.CustomerRepository;
import cs544.edu.utilities.EmailService;
import cs544.edu.vehicles.VehicleRepository;

@Service

public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public Page<Reservation> getCustomerReservations(Long customerId, int page) {
        return reservationRepository.findByCustomer_IdOrderByIdDesc(customerId, gotoPage(page));
    }

    @Override
    @Transactional
    public Page<Reservation> getCustomerReservationsById(String customerLicenceId, int page) {
        Long recordId = -1L;

        try {
            recordId = Long.parseLong(customerLicenceId);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();

        }

        return reservationRepository.findByCustomer_licenseNumberContainingOrIdOrderByIdDesc(customerLicenceId, recordId, gotoPage(page));
    }

    @Override
    @Transactional
    public Page<Reservation> getAllReservations(int page) {

        return reservationRepository.findByOrderByIdDesc(gotoPage(page));
    }


    @Override
    @Transactional
    public void makeReservation(Reservation reservation, Long customerId, Vehicle vehicleToReserve) {


        Customer customer = customerRepository.findOne(customerId);
        reservation.setCustomer(customer);
        reservation.setStatus(ReservationStatus.RESERVED);
        reservation.setReservationDate(new Date());
        reservation.setVehicle(vehicleToReserve);
        reservationRepository.save(reservation);

        vehicleToReserve.setStatus(VehicleStatus.RESERVED);
        vehicleRepository.save(vehicleToReserve);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.setStatus(ReservationStatus.CANCELLED);
        Vehicle vehicle = reservation.getVehicle();
        vehicle.setStatus(VehicleStatus.AVAILABLE);

        reservationRepository.save(reservation);
        vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Reservation getById(Long reservationId) {
        return reservationRepository.findOne(reservationId);
    }

    @Override
    @Transactional
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public Vehicle getVehicleToReserve(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);

        if (vehicle == null || vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new CannotReserveVehicleException(" vehicle cannot be reserved ");
        }


        return vehicle;
    }

    @Override
    @Transactional
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    private Pageable gotoPage(int page) {
        PageRequest request = new PageRequest(page, 10);
        return request;
    }

    @Transactional
    public List<String> cancelDelayedReservations() {
        List<String> emailsToSend = new ArrayList<>();

        List<Reservation> reservationList = reservationRepository.findByPickupDateLessThanAndStatus(new Date(), ReservationStatus.RESERVED);

        for(int i = 0; i < reservationList.size(); i++) {

            Reservation reservation = reservationList.get(i);
            Vehicle vehicle = reservation.getVehicle();
            vehicle.setStatus(VehicleStatus.AVAILABLE);
            reservation.setStatus(ReservationStatus.CANCELLED);

            emailsToSend.add(reservation.getCustomer().getEmployee().getUsername());

            vehicleRepository.save(vehicle);
            reservationRepository.save(reservation);
        }

        return emailsToSend;
    }
}
