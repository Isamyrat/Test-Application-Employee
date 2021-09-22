package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Address;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.AddressRepository;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AddressService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    public List<Address> addAddress(String country, String city,String district, String street,String house, String apartment, Long idEmployee) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + idEmployee));

        Address address = new Address(country,city,district, street, house, apartment, employee);
        addressRepository.save(address);
        return addressRepository.findAll();
    }

    public Address findAddressById(Long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return address;
    }

    public Address updateAddress(Long id, String country, String city, String district, String street, String house, String apartment){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));


        address.setCountry(country);
        address.setCity(city);
        address.setDistrict(district);
        address.setStreet(street);
        address.setHouse(house);
        address.setApartment(apartment);

        Address updateAddress = addressRepository.save(address);
        return updateAddress;
    }

    public ResponseEntity<Map<String, Boolean>> deleteAddress(Long id) {
        addressRepository.deleteAddressById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
