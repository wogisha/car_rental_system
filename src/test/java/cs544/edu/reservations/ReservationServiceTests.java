package cs544.edu.reservations;


import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.entities.enums.VehicleType;
import cs544.edu.userMgmt.repository.CustomerRepository;
import cs544.edu.vehicles.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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

    @Autowired
    private VehicleRepository vehicleRepository;


    @Test
    public void testReservationServiceCreated() {
        assertNotNull(reservationService);
    }


    public Reservation createReservation() {

        Reservation reservation = new Reservation();

        reservation.setStatus(ReservationStatus.RESERVED);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+ 1);
        Date date = calendar.getTime();

        reservation.setPickupDate(date);
        reservation.setReturnDate(date);

        return reservation;
    }

    @Test(expected = NullPointerException.class)
    public void testSavesReservation() {
        Reservation reservation = createReservation();


        reservationService.makeReservation(reservation, 1L, null);

        assertNotNull(reservation.getId());

    }
    @Test
    public void testSaveVehicle(){
        int sizeBefore = vehicleRepository.findAll().size();
        Vehicle vehicle = createTestVehicle();
        vehicleRepository.save(vehicle);

        int sizeAfter = vehicleRepository.findAll().size();

        assertEquals(sizeBefore+ 1,sizeAfter);

        Reservation reservation = createReservation();


        reservationService.makeReservation(reservation, 1L, vehicle);

        assertNotNull(reservation.getId());



    }

    @Test(expected = NullPointerException.class)
    public void testReservationCancelled() {
        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, 1L, null);
        assertEquals(reservation.getStatus(), ReservationStatus.RESERVED);

        reservationService.cancelReservation(reservation.getId());

        Reservation reservationCancelled = reservationService.getById(reservation.getId());

        assertEquals(reservationCancelled.getStatus(), ReservationStatus.CANCELLED);

    }

    @Test(expected = NullPointerException.class)
    public void testReservationUpdated() {
        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, 1L, null);

        reservation.setStatus(ReservationStatus.COMPLETED);

        reservationService.updateReservation(reservation);

        Reservation reservationUpdated = reservationService.getById(reservation.getId());

        assertEquals(reservationUpdated.getStatus(), ReservationStatus.COMPLETED);
    }


    @Test(expected = NullPointerException.class)
    public void testGetReservationsByCustomerId() {
        Customer customer = new Customer();

        customerRepository.save(customer);

        Page<Reservation> reservationList = reservationService.getCustomerReservations(customer.getId(),0);

        assertEquals(reservationList.getTotalPages(), 0);

        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, customer.getId(),null );

        reservationList = reservationService.getCustomerReservations(customer.getId(),0);

        assertEquals(reservationList.getTotalPages(), 1);
    }

    @Test
    public void testCancellingReservation() {
        Customer customer = new Customer();

        customerRepository.save(customer);

        Page<Reservation> reservationList = reservationService.getCustomerReservations(customer.getId(),0);

        assertEquals(reservationList.getTotalPages(), 0);

        Reservation reservation = createReservation();

        Vehicle vehicle = createTestVehicle();
        vehicleRepository.save(vehicle);


        reservationService.makeReservation(reservation, customer.getId(),vehicle );

        reservationList = reservationService.getCustomerReservations(customer.getId(),0);

        assertEquals(reservationList.getTotalPages(), 1);

        reservationService.cancelReservation(reservation.getId());

        Reservation cancelledReservation = reservationService.getById(reservation.getId());

        assertEquals(cancelledReservation.getStatus(),ReservationStatus.CANCELLED);

        Vehicle vehicleReserved = vehicleRepository.findOne(vehicle.getId());

        assertEquals(vehicleReserved.getStatus(),VehicleStatus.AVAILABLE);

    }


    private Vehicle createTestVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand("Toyota");
        vehicle.setCondition("new");
        vehicle.setDailyPrice(10);
        vehicle.setFuelType(FuelType.PETROL);
        vehicle.setModel("has bacl");
        vehicle.setPlateNumber("1234");
        vehicle.setSeatQuantity(4);
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicle.setType(VehicleType.Hatchback);
        return vehicle;
    }

}
