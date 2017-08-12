package cs544.edu.vehicles.services;


import java.util.List;

import cs544.edu.entities.Vehicle;   


public interface VehicleService {
	public void save(Vehicle vehicle);
	public List<Vehicle> getAllVehicles();
	public void delete(long vehicleId);
	public void update(Vehicle vehicle);
	
	public List<Vehicle> search(Integer seats, Double minPrice, Double maxPrice, Boolean isAvailable);
	public List<Vehicle> isAvailable(Boolean flag);
	public Vehicle findByVehicleId(long vehicleId);
	

}
