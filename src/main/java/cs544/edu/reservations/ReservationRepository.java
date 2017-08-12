package cs544.edu.reservations;

import java.util.List;

import cs544.edu.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByCustomer_Id(Long customerId);
}
