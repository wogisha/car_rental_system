package cs544.edu.rental;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.edu.entities.Rent;
import cs544.edu.entities.enums.RentStatus;

public interface RentalRepository extends JpaRepository<Rent, Long> {

//	List<Rent> findByStatus(RentStatus rentStatus);
	Page<Rent> findByOrderByIdDesc(Pageable pageRequest);

	Page<Rent> findByCustomer_IdOrderByIdDesc(Long rentId, Pageable pageable);

	Page<Rent> findByCustomer_licenseNumberContainingOrIdOrderByIdDesc(String customerLicenseNumber, Long id,
			Pageable pageable);

}
