package cs544.edu.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String licenseNumber;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String nationality;
    @NotEmpty
    private String country;

    private String city;

    private String mobileNumber;

    @OneToOne
    private Employee employee;

    @OneToMany(mappedBy = "customer")
    List<Rent> rentList;

    @OneToMany(mappedBy = "customer")
    List<Reservation> reservationList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
