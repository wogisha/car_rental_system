package cs544.edu.reservations;

import java.util.List;

import cs544.edu.CarRentalSystemApplication;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.enums.ReservationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource("classpath:/test.properties")
public class ReservationServiceTests {
    @Autowired
    private ReservationService reservationService;



    @Test
    public void testReservationServiceCreated(){
        assertNotNull(reservationService);
    }

    @Test
    public void testSavesReservation(){
        int reservationsBefore = reservationService.getAllReservations().size();

        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.NEW);

        reservationService.makeReservation(reservation,1L);

        int reservationsAfter = reservationService.getAllReservations().size();

        assertEquals(reservationsBefore + 1, reservationsAfter);



    }

}
