package cs544.edu.entities;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import cs544.edu.entities.enums.FuelProvider;
import cs544.edu.entities.enums.RentStatus;

@Entity
public class Rent {
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty (message="{rent.rentDate}")
	private Date rentDate;
	@NotEmpty (message="{rent.returnDate}")
	private Date returnDate;
	
	private int totalRentDay;
	private int dailyRentFee;
	@Enumerated(EnumType.STRING)
	private FuelProvider fuelProvidedBy;
	private long fuelCharge;
	private double totalPaid;
	private double refund;
	private double extraPaid;
	@Enumerated(EnumType.STRING)
	private RentStatus rentStatus;

	public RentStatus getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(RentStatus rentStatus) {
		this.rentStatus = rentStatus;
	}

	@ManyToOne
	private Vehicle vehicle;

	@OneToOne
	private Reservation reservation;

	@ManyToOne
	private Customer customer;

	@ManyToOne
	private Employee employee;

	public Rent() {

	}

	public Rent(String cusName, Date rentDate) {
		this.customer = new Customer();
		this.customer.setFullName(cusName);
		this.rentDate = rentDate;
	}

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

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public double getRefund() {
		return refund;
	}

	public void setRefund(double refund) {
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

	@Transient
	public double calculateCost(Date start, Date end, double price) {

		int days = (int) (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
		double totPrice = price + price * days;
		System.out.println(totPrice);
		return totPrice;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public double getExtraPaid() {
		return extraPaid;
	}

	public void setExtraPaid(double extraPaid) {
		this.extraPaid = extraPaid;
	}

	public boolean isRented() {
		if(rentStatus.equals(RentStatus.RENTED)) {
			return true;
		}
		return false;
	}

}
