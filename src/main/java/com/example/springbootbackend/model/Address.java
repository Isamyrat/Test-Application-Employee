package com.example.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;
    private String city;
    private String district;
    private String street;
    private String house;
    private String apartment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employees_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Employee employeesAddress;

    public Address() {
    }

    public Address(String country, String city, String district, String street, String house, String apartment, Employee employeesAddress) {
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.employeesAddress = employeesAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Employee getEmployeesAddress() {
        return employeesAddress;
    }

    public void setEmployeesAddress(Employee employeesAddress) {
        this.employeesAddress = employeesAddress;
    }
}
