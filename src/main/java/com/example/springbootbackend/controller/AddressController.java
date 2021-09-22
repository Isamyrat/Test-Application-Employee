package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Address;
import com.example.springbootbackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public List<Address> getAll(){
        return addressService.getAll();
    }

    @PostMapping("/address")
    public List<Address> addAddress(@RequestBody String country, String city,String district, String street,String house, String apartment, Long idEmployee) {
        return addressService.addAddress(country,city,district,street,house,apartment,idEmployee);
    }

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.findAddressById(id);
    }

    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody String country, String city,String district, String street,String house, String apartment) {
        return addressService.updateAddress(id, country, city,district,street,house,apartment);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAddress(@PathVariable Long id) {

        return addressService.deleteAddress(id);
    }

}
