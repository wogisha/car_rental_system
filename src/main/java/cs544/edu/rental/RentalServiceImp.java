package cs544.edu.rental;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Rent;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.RentStatus;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.reservations.repositories.ReservationRepository;
import cs544.edu.vehicles.VehicleRepository;

//@PreAuthorize("hasRole('EMPLOYEE','MANAGER')")
@Service
@Transactional
public class RentalServiceImp implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public void saveRent(Rent rent) {

		Reservation reservation = rent.getReservation();
		reservation.setStatus(ReservationStatus.COMPLETED);

		Vehicle vehicle = rent.getVehicle();
		vehicle.setStatus(VehicleStatus.RENTED);

		rent.setRentStatus(RentStatus.RENTED);

		rentalRepository.save(rent);
		reservationRepository.save(reservation);
		vehicleRepository.save(vehicle);
	}

	@Override
	public List<Rent> getAll() {
		return rentalRepository.findAll();
	}

	@Override
	public Rent getOne(long id) {
		return rentalRepository.getOne(id);
	}

	@Override
	public void returnedCar(Rent rent) {
		if (rent.getReservation().getStatus().equals(ReservationStatus.COMPLETED)) {
			if (rent.getReservation().getVehicle().getStatus().equals(VehicleStatus.RENTED)) {
				Vehicle vehicle = rent.getVehicle();
				vehicle.setStatus(VehicleStatus.AVAILABLE);
				rent.setRentStatus(RentStatus.RETURNED);
			}
		}
	}

	@Override
	public Page<Rent> getAllCustomersRentCar(int page) {
		return rentalRepository.findByOrderByIdDesc(gotoPage(page));
	}

//	Dont use
//	@Override
//	public Page<Rent> getCustomersRentCar(Long customerId, int page) {
//		return rentalRepository.findByCustomer_IdOrderByIdDesc(customerId, gotoPage(page));
//	}

	@Override
	public Page<Rent> getCustomerRentCarById(String customerLicenceId, int page) {
		Long recordId = -1L;

		try {
			recordId = Long.parseLong(customerLicenceId);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();

		}

		return rentalRepository.findByCustomer_licenseNumberContainingOrIdOrderByIdDesc(customerLicenceId,
				recordId, gotoPage(page));
	}
	
	private Pageable gotoPage(int page) {
		PageRequest request = new PageRequest(page, 10);
		return request;
	}

	@Override
	public List<Rent> getCustomerReturnCar() {		
		return rentalRepository.findByRentStatus(RentStatus.RETURNED);
	}

	
	

}
