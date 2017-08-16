package cs544.edu.reservations.exceptions;

public class CannotReserveVehicleException extends RuntimeException {
    public CannotReserveVehicleException(String message) {
        super(message);
    }
}
