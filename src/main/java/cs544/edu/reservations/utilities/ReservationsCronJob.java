package cs544.edu.reservations.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cs544.edu.reservations.services.ReservationService;
import cs544.edu.utilities.EmailService;

public class ReservationsCronJob {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 6 * * *")
    public void sendCancelledReservations() {

        List<String> cancelledReservations = reservationService.cancelDelayedReservations();

        for (String email : cancelledReservations) {
            emailService.sendSimpleMessage(email, "Delayed Reservation", "Your car reservation with us has been cancelled");
        }

    }

}
