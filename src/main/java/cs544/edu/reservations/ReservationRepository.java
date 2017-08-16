package cs544.edu.reservations;


import cs544.edu.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findByOrderByIdDesc(Pageable pageRequest);

    Page<Reservation> findByCustomer_IdOrderByIdDesc(Long customerId, Pageable pageable);

    Page<Reservation> findByCustomer_licenseNumberContainingOrIdOrderByIdDesc(String customerLicenseNumber, Long id, Pageable pageable);


}
