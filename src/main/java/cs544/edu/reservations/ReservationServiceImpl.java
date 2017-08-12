package cs544.edu.reservations;


import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;

import java.util.List;

import cs544.edu.entities.enums.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getCustomerReservations(Long customerId)
    {
        return reservationRepository.findByCustomer_Id(customerId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    @Override
    public void makeReservation(Reservation reservation, Long customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
//        reservation.setCustomer(customer);
        reservation.setStatus(ReservationStatus.NEW);
        reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }
}
