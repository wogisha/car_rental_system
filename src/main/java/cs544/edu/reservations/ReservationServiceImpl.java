package cs544.edu.reservations;


import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.userMgmt.CustomerRepository;
import cs544.edu.vehicles.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Page<Reservation> getCustomerReservations(Long customerId, int page) {
        return reservationRepository.findByCustomer_IdOrderByIdDesc(customerId,gotoPage(page));
    }

    @Override
    public Page<Reservation> getCustomerReservationsById(String customerLicenceId, int page) {
        Long recordId = -1L;

        try {
            recordId = Long.parseLong(customerLicenceId);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();

        }

        return reservationRepository.findByCustomer_licenseNumberContainingOrIdOrderByIdDesc(customerLicenceId,recordId,gotoPage(page));
    }

    @Override
    public Page<Reservation> getAllReservations(int page) {

        return reservationRepository.findByOrderByIdDesc(gotoPage(page));
    }


    @Override
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
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.setStatus(ReservationStatus.CANCELLED);
        Vehicle vehicle = reservation.getVehicle();
        vehicle.setStatus(VehicleStatus.AVAILABLE);

        reservationRepository.save(reservation);
        vehicleRepository.save(vehicle);
    }

    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.findOne(reservationId);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Vehicle getVehicleToReserve(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findOne(vehicleId);

        if (vehicle == null || vehicle.getStatus() != VehicleStatus.AVAILABLE) {
            throw new CannotReserveVehicleException(" vehicle cannot be reserved ");
        }


        return vehicle;
    }

    @Override
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    private Pageable gotoPage(int page)
    {
        PageRequest request = new PageRequest(page, 10);
        return request;
    }
}
