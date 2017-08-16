package cs544.edu.rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cs544.edu.entities.Rent;

public interface RentalRepository extends JpaRepository<Rent, Long> {

	Page<Rent> findByOrderByIdDesc(Pageable pageRequest);

	Page<Rent> findByCustomer_IdOrderByIdDesc(Long customerId, Pageable pageable);

	Page<Rent> findByCustomer_licenseNumberContainingOrIdOrderByIdDesc(String customerLicenseNumber, Long id,
			Pageable pageable);

}
