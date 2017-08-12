package cs544.edu.reservations;


import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;


    @Override
    public List<Reservation> getCustomerReservations(Customer customer) {
        return null;
    }

    @Override
    public void makeReservation(Reservation reservation, Customer customer) {

    }

    @Override
    public void cancelReservation(Reservation reservation) {

    }
}
