package cs544.edu.reservations;


import java.util.List;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.userMgmt.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource("classpath:/test.properties")
public class ReservationServiceTests {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void testReservationServiceCreated() {
        assertNotNull(reservationService);
    }

    public Reservation createReservation() {

        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.NEW);

        return reservation;
    }

    @Test
    public void testSavesReservation() {
        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, 1L);

        assertNotNull(reservation.getId());

    }

    @Test
    public void testReservationCancelled() {
        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, 1L);
        assertEquals(reservation.getStatus(), ReservationStatus.NEW);

        reservationService.cancelReservation(reservation.getId());

        Reservation reservationCancelled = reservationService.getById(reservation.getId());

        assertEquals(reservationCancelled.getStatus(), ReservationStatus.CANCELLED);

    }

    @Test
    public void testReservationUpdated() {
        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, 1L);

        reservation.setStatus(ReservationStatus.COMPLETED);

        reservationService.updateReservation(reservation);

        Reservation reservationUpdated = reservationService.getById(reservation.getId());

        assertEquals(reservationUpdated.getStatus(), ReservationStatus.COMPLETED);
    }


    @Test
    public void testGetReservationsByCustomerId() {
        Customer customer = new Customer();

        customerRepository.save(customer);

        List<Reservation> reservationList = reservationService.getCustomerReservations(customer.getId());

        assertEquals(reservationList.size(), 0);

        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, customer.getId());

        reservationList = reservationService.getCustomerReservations(customer.getId());

        assertEquals(reservationList.size(), 1);
    }

}
