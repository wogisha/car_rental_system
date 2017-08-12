package cs544.edu.vehicles;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Vehicle;


@Service
@Transactional
public class VehicleServiceImpl  {

	@Autowired
	private VehicleRepository vehicleDao;
	

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(Vehicle vehicle) {
		vehicleDao.save(vehicle);
	}

/*	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleDao.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(long vehicleId) {
		vehicleDao.delete(vehicleId);
		
	}

	@Override
	public void update(Vehicle vehicle) {
		vehicleDao.update(vehicle);
		
	}

	

	@Override
	public List<Vehicle> search(Integer seats, Double minPrice, Double maxPrice, Boolean isAvailable) {
		return vehicleDao.searh(seats,minPrice,maxPrice,isAvailable);
	}

	@Override
	public List<Vehicle> isAvailable(Boolean flag) {
		return vehicleDao.isAvaialble(flag);
	}

	@Override
	public Vehicle findByVehicleId(long vehicleId) {
		return vehicleDao.findOne(vehicleId);
	}   */

	
}
