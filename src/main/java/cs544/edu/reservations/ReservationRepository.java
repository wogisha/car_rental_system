package cs544.edu.reservations;

import cs544.edu.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wogisha on 12/08/2017.
 */
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
