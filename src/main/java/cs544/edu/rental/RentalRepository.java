package cs544.edu.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import cs544.edu.entities.Rent;


public interface RentalRepository extends JpaRepository<Rent, Long> {
	
}
