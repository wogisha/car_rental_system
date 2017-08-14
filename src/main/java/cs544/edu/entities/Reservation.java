package cs544.edu.entities;

import cs544.edu.entities.enums.ReservationStatus;
import cs544.edu.reservations.CustomFuture;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue

    private long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CustomFuture(groups = {NewReservation.class})

    private Date pickupDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CustomFuture(groups = {NewReservation.class},todayDate = false,message = "Should be in the future")
    private Date returnDate;

    @Temporal(TemporalType.DATE)
    private Date reservationDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Vehicle vehicle;

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", pickupDate=" + pickupDate +
                ", returnDate=" + returnDate +
                ", reservationDate=" + reservationDate +
                ", vehicle=" + vehicle +
                ", rent=" + rent +
                ", status=" + status +
                '}';
    }

    public boolean isNewReservation() {
        return status.equals(ReservationStatus.RESERVED);
    }

    public interface NewReservation {

    }
}
