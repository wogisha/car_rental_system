package cs544.edu.rental;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.edu.entities.Rent;

@Service
@Transactional
public class RentalServiceImp implements RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Override
	public void saveRent(Rent rent) {
		rentalRepository.save(rent);
	}

}
