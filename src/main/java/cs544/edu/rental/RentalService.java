package cs544.edu.rental;

import cs544.edu.entities.Rent;

import java.util.List;

import org.springframework.data.domain.Page;

public interface RentalService {

	public void saveRent(Rent rent);

	List<Rent> getAll();

	Rent getOne(long id);

	public void returnedCar(Rent rent);

	public Page<Rent> getAllCustomersRentCar(int page);

//	public Page<Rent> getCustomersRentCar(Long customerId, int page);

	public Page<Rent> getCustomerRentCarById(String customerLicenceId, int page);

}
