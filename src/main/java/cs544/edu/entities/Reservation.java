package cs544.edu.entities;

import cs544.edu.entities.enums.ReservationStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private long id;

    private Date pickupDate;
    private Date returnDate;
    private Date reservationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne (fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @OneToOne(mappedBy = "reservation",fetch = FetchType.LAZY)
    private Rent rent;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservationStatus")
    private ReservationStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
