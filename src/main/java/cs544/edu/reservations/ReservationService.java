package cs544.edu.reservations;


import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import org.springframework.data.domain.Page;

public interface ReservationService {
    Page<Reservation> getCustomerReservations(Long customerId, int page);
    Page<Reservation> getCustomerReservationsById(String customerLicenceId, int page);

    Page<Reservation> getAllReservations(int page);

    void makeReservation(Reservation reservation, Long customerId, Vehicle vehicleToReserve);

    void cancelReservation(Long reservationId);

    Reservation getById(Long reservationId);

    void updateReservation(Reservation reservation);

    Vehicle getVehicleToReserve(Long vehicleId);

}
