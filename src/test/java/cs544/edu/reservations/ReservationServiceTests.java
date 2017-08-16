package cs544.edu.reservations;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs544.edu.entities.Customer;
import cs544.edu.entities.Employee;
import cs544.edu.entities.Reservation;
import cs544.edu.entities.Vehicle;
import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.entities.enums.UserRole;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.entities.enums.VehicleType;
import cs544.edu.reservations.services.ReservationService;
import cs544.edu.userMgmt.repository.CustomerRepository;
import cs544.edu.userMgmt.repository.EmployeeRepository;
import cs544.edu.vehicles.VehicleRepository;

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

    @Autowired
    private EmployeeRepository employeeRepository;


    private Employee createEmployee() {

        Employee employee = new Employee();
        employee.setFullName("ne");
        employee.setUsername("wogisha@gmail.com");
        employee.setRole(UserRole.ROLE_CUSTOMER);
        employee.setAddress("adddess");
        employee.setEnabled(true);
        employee.setPassword("1234567890");

        return employee;
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFullName("123");
        customer.setNationality("nation");
        customer.setLicenseNumber("321");
        customer.setCountry("country");
        return customer;
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

    @Test
    public void testReservationServiceCreated() {
        assertNotNull(reservationService);
    }


    public Reservation createReservation() {

        Reservation reservation = new Reservation();

        reservation.setStatus(ReservationStatus.RESERVED);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
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
    public void testSaveVehicle() {
        int sizeBefore = vehicleRepository.findAll().size();
        Vehicle vehicle = createTestVehicle();
        vehicleRepository.save(vehicle);

        int sizeAfter = vehicleRepository.findAll().size();

        assertEquals(sizeBefore + 1, sizeAfter);

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
        Customer customer = createCustomer();

        customerRepository.save(customer);

        Page<Reservation> reservationList = reservationService.getCustomerReservations(customer.getId(), 0);

        assertEquals(reservationList.getTotalPages(), 0);

        Reservation reservation = createReservation();

        reservationService.makeReservation(reservation, customer.getId(), null);

        reservationList = reservationService.getCustomerReservations(customer.getId(), 0);

        assertEquals(reservationList.getTotalPages(), 1);
    }

    @Test
    public void testCancellingReservation() {
        Customer customer = createCustomer();
        customerRepository.save(customer);

        Page<Reservation> reservationList = reservationService.getCustomerReservations(customer.getId(), 0);

        assertEquals(reservationList.getTotalPages(), 0);

        Reservation reservation = createReservation();

        Vehicle vehicle = createTestVehicle();
        vehicleRepository.save(vehicle);


        reservationService.makeReservation(reservation, customer.getId(), vehicle);

        reservationList = reservationService.getCustomerReservations(customer.getId(), 0);

        assertEquals(reservationList.getTotalPages(), 1);

        reservationService.cancelReservation(reservation.getId());

        Reservation cancelledReservation = reservationService.getById(reservation.getId());

        assertEquals(cancelledReservation.getStatus(), ReservationStatus.CANCELLED);

        Vehicle vehicleReserved = vehicleRepository.findOne(vehicle.getId());

        assertEquals(vehicleReserved.getStatus(), VehicleStatus.AVAILABLE);

    }

    @Transactional
    @Test
    public void testCancelDelayedReservations() {
        Employee employee = createEmployee();
        Customer customer = createCustomer();
        customer.setEmployee(employee);
        Vehicle vehicle = createTestVehicle();
        Reservation reservation = createReservation();

        customerRepository.save(customer);
        employeeRepository.save(employee);
        vehicleRepository.save(vehicle);

        reservationService.makeReservation(reservation,customer.getId(),vehicle);

        List<String> delayedEmails = reservationService.cancelDelayedReservations();

        assertEquals(delayedEmails.size(),0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        Date date = calendar.getTime();

        reservation.setPickupDate(date);

        System.out.println(new Date());
        System.out.println(date);

        reservationService.updateReservation(reservation);


        delayedEmails = reservationService.cancelDelayedReservations();

        assertEquals(delayedEmails.size(),1);

        delayedEmails = reservationService.cancelDelayedReservations();

        assertEquals(delayedEmails.size(),0);



    }


}
