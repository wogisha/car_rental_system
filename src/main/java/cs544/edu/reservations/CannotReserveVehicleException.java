package cs544.edu.reservations;

public class CannotReserveVehicleException extends RuntimeException {
    public CannotReserveVehicleException(String message) {
        super(message);
    }
}
