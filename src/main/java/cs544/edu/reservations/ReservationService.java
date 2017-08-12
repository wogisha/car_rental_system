package cs544.edu.reservations;

import com.sun.tools.javac.util.List;
import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;

/**
 * Created by wogisha on 12/08/2017.
 */
public interface ReservationService {
    List<Reservation> getCustomerReservations(Customer customer);

    void makeReservation(Reservation reservation, Customer customer);

    void cancelReservation(Reservation reservation);
}
