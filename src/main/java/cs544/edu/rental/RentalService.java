package cs544.edu.rental;

import cs544.edu.entities.Rent;

import java.util.List;

public interface RentalService {
	
	public void saveRent(Rent rent);

	List<Rent> getAll();
	
	Rent getOne(long id);
	
	public void returnedCar(Rent rent);
	
	public void updateRent(Rent rent);
	
	
}
