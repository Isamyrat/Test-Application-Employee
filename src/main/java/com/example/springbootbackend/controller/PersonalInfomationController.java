package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.PersonalInformation;
import com.example.springbootbackend.service.PersonalInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class PersonalInfomationController {
    @Autowired
    private PersonalInformationService personalInformationService;

    @GetMapping("/personalInformation")
    public List<PersonalInformation> getAll(){
        return personalInformationService.getAll();
    }

    @PostMapping("/personalInformation")
    public List<PersonalInformation> addPersonalInformation(@RequestBody Short age, String gender,String position, Long idEmployee) {
        return personalInformationService.addPersonalInformation(age, gender,position, idEmployee);
    }

    @GetMapping("/personalInformation/{id}")
    public PersonalInformation getPersonalInformationById(@PathVariable Long id) {
        return personalInformationService.findPersonalInformationById(id);
    }

    @PutMapping("/personalInformation/{id}")
    public PersonalInformation updatePersonalInformation(@PathVariable Long id, @RequestBody Short age, String gender,String position) {
        return personalInformationService.updatePersonalInformation(id, age, gender,position);
    }

    @DeleteMapping("/personalInformation/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePersonalInformation(@PathVariable Long id) {

        return personalInformationService.deletePersonalInformation(id);
    }

}
