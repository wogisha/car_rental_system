package cs544.edu.rental;

import javax.transaction.Transactional;

import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.RentStatus;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.reservations.ReservationRepository;
import cs544.edu.vehicles.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Rent;

import java.util.List;

@Service
@Transactional
public class RentalServiceImp implements RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@PreAuthorize("hasRole('EMPLOYEE','MANAGER')")
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

	@PreAuthorize("hasRole('EMPLOYEE','MANAGER')")
	@Override
	public void returnedCar(Rent rent) {
		if(rent.getReservation().getStatus().equals(ReservationStatus.COMPLETED)) {
			if(rent.getReservation().getVehicle().getStatus().equals(VehicleStatus.RENTED)) {
				Vehicle vehicle = rent.getVehicle();
				vehicle.setStatus(VehicleStatus.AVAILABLE);
				rent.setRentStatus(RentStatus.RETURNED);				
			}
		}
	}

	
	
}
