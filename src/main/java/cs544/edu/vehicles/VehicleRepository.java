package cs544.edu.vehicles;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.VehicleStatus;

public interface VehicleRepository extends CrudRepository<Vehicle, Long>{

	public List<Vehicle> findByStatus(VehicleStatus available); 
	public List<Vehicle> findByFuelType(FuelType type); 
	
	public List<Vehicle> findByBrand(String brand); 
	public List<Vehicle> findBySeatQuantity(int seatQuantity); 
	
}
