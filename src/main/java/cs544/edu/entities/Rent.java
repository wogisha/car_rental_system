package cs544.edu.entities;


import cs544.edu.entities.enums.FuelProvider;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rent {
    @Id
    @GeneratedValue
    private long id;
    private Date rentDate;
    private Date returnDate;
    private int totalRentDay;
    private int dailyRentFee;
    @Enumerated(EnumType.STRING)
    private FuelProvider fuelProvidedBy;
    private long fuelCharge;
    private long totalPaid;
    private long refund;

    
    public Rent() {
    	
    }
    
    public Rent(String cusName, Date rentDate) {
    	this.customer = new Customer();
    	this.customer.setFullName(cusName);
    	this.rentDate = rentDate;
    }
    
    @OneToOne
    private Reservation reservation;

    @ManyToOne(cascade= CascadeType.PERSIST)
    private Customer customer;

    @ManyToOne
    private Employee employee;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getTotalRentDay() {
        return totalRentDay;
    }

    public void setTotalRentDay(int totalRentDay) {
        this.totalRentDay = totalRentDay;
    }

    public int getDailyRentFee() {
        return dailyRentFee;
    }

    public void setDailyRentFee(int dailyRentFee) {
        this.dailyRentFee = dailyRentFee;
    }

    public FuelProvider getFuelProvidedBy() {
        return fuelProvidedBy;
    }

    public void setFuelProvidedBy(FuelProvider fuelProvidedBy) {
        this.fuelProvidedBy = fuelProvidedBy;
    }

    public long getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(long fuelCharge) {
        this.fuelCharge = fuelCharge;
    }

    public long getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(long totalPaid) {
        this.totalPaid = totalPaid;
    }

    public long getRefund() {
        return refund;
    }

    public void setRefund(long refund) {
        this.refund = refund;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


}
