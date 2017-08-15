package cs544.edu.rental;

import javax.transaction.Transactional;

import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.reservations.ReservationRepository;
import cs544.edu.vehicles.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Rent;

import java.util.Date;
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
	
	@Override
	public void saveRent(Rent rent) {

		Reservation reservation = rent.getReservation();
		reservation.setStatus(ReservationStatus.COMPLETED);

		Vehicle vehicle = rent.getVehicle();
		vehicle.setStatus(VehicleStatus.RENTED);

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
	public boolean returnedCar(Rent rent) {
		if(rent.getReservation().getStatus().equals(ReservationStatus.COMPLETED)) {
			if(rent.getReservation().getVehicle().getStatus().equals(VehicleStatus.RENTED)) {
				Vehicle vehicle = rent.getVehicle();
				vehicle.setStatus(VehicleStatus.AVAILABLE);
				
				if(rent.getReturnDate().compareTo(new Date())!=1) {
					Date tempDate= rent.getReturnDate();
					rent.setReturnDate(new Date());
					double temp = rent.calculateCost(tempDate, new Date(), rent.getReservation().getVehicle().getDailyPrice());
					if(temp>0)
						rent.setExtraPaid(temp);			
					rent.setRefund(temp + rent.getTotalPaid());
				}
			}
			return true;
		}
		return false;
	}
	
}
