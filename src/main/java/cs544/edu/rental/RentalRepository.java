package cs544.edu.rental;

import org.springframework.data.repository.CrudRepository;
import cs544.edu.entities.Rent;


public interface RentalRepository extends CrudRepository<Rent, Long> {

}
