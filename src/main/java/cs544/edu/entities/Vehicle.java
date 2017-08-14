package cs544.edu.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cs544.edu.entities.enums.FuelType;
import cs544.edu.entities.enums.VehicleStatus;
import cs544.edu.entities.enums.VehicleType;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private long id;
    private String brand;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private String model;
    private String plateNumber;
    private int seatQuantity;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "carCondition")
    private String condition;

    private double dailyPrice;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    
//    @OneToMany(mappedBy= "vehicle")
//    private List<Rent> rent;

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(int seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }


//	public List<Rent> getRent() {
//		return rent;
//	}
//
//
//	public void setRent(List<Rent> rent) {
//		this.rent = rent;
//	}
    
    
}
