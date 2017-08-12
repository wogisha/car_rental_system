package cs544.edu.vehicles;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.edu.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
