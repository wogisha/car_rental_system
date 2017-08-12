package cs544.edu.reservations;


import java.util.List;

import cs544.edu.entities.Reservation;

public interface ReservationService {
    List<Reservation> getCustomerReservations(Long customerId);

    List<Reservation> getAllReservations();

    void makeReservation(Reservation reservation, Long customerId);

    void cancelReservation(Long reservationId);

    Reservation getById(Long reservationId);

    void updateReservation(Reservation reservation);
}
