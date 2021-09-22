package com.example.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "personal_information")
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private short age;
    private String gender;
    private String position;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employees_id",referencedColumnName = "id",nullable = false)
    @JsonBackReference
    private Employee employeeInformation;

    public PersonalInformation() {
    }


    public PersonalInformation(short age, String gender, String position, Employee employeeInformation) {
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.employeeInformation = employeeInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getEmployeeInformation() {
        return employeeInformation;
    }

    public void setEmployeeInformation(Employee employeeInformation) {
        this.employeeInformation = employeeInformation;
    }
}
