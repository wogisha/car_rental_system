package cs544.edu.vehicles;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.VehicleStatus;

@Service
@Transactional
public class VehicleServiceImpl {

	@Autowired
	private VehicleRepository vehicleRepository;

	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public List<Vehicle> getAllVehicles() {
		return (List<Vehicle>) vehicleRepository.findAll();
	}

	public Vehicle findByVehicleId(long vehicleId) {
		return vehicleRepository.findOne(vehicleId);
	}

	public void delete(long id) {
		vehicleRepository.delete(id);
	}

	public void edit(Vehicle vehicle) {
		vehicleRepository.save(vehicle);

	}

	public List<Vehicle> findByStatus(VehicleStatus available) {
		return vehicleRepository.findByStatus(available);
	}

	public List<Vehicle> findByfuelType(FuelType type) {

		return vehicleRepository.findByFuelType(type);
	}

	public List<Vehicle> findByBrand(String brand) {

		return vehicleRepository.findByBrand(brand);
	}

	public List<Vehicle> findBySeatQuantity(int seatQuantity) {

		return vehicleRepository.findBySeatQuantity(seatQuantity);
	}

	/*
	 * public List<Vehicle> findOneWithName(String available) { return
	 * vehicleDao.findAll(Iterable<String> available); }
	 */

	/*
	 * @Override
	 * 
	 * @PreAuthorize("hasRole('ROLE_ADMIN')") public void delete(long vehicleId) {
	 * vehicleDao.delete(vehicleId);
	 * 
	 * }
	 * 
	 * @Override public void update(Vehicle vehicle) { vehicleDao.update(vehicle);
	 * 
	 * }
	 * 
	 * @Override public List<Vehicle> search(Integer seats, Double minPrice, Double
	 * maxPrice, Boolean isAvailable) { return
	 * vehicleDao.searh(seats,minPrice,maxPrice,isAvailable); }
	 * 
	 * @Override public List<Vehicle> isAvailable(Boolean flag) { return
	 * vehicleDao.isAvaialble(flag); }
	 */

}
