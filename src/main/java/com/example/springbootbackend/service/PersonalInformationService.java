package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.model.PersonalInformation;
import com.example.springbootbackend.repository.EmployeeRepository;
import com.example.springbootbackend.repository.PersonalInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PersonalInformationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    public List<PersonalInformation> getAll(){
        return personalInformationRepository.findAll();
    }

    public List<PersonalInformation> addPersonalInformation(Short age, String gender,String position, Long idEmployee) {
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + idEmployee));

        PersonalInformation personalInformation1 = new PersonalInformation(age,gender,position, employee);
        personalInformationRepository.save(personalInformation1);
        return personalInformationRepository.findAll();
    }

    public PersonalInformation findPersonalInformationById(Long id){
        PersonalInformation personalInformation = personalInformationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonalInformation not exist with id:" + id));
        return personalInformation;
    }

    public PersonalInformation updatePersonalInformation(Long idEmployee, Short age, String gender,String position){
        PersonalInformation personalInformation = personalInformationRepository.findById(idEmployee)
                .orElseThrow(() -> new ResourceNotFoundException("PersonalInformation not exist with id:" + idEmployee));

        personalInformation.setAge(age);
        personalInformation.setGender(gender);
        personalInformation.setPosition(position);


        PersonalInformation updatePersonalInformation = personalInformationRepository.save(personalInformation);
        return updatePersonalInformation;
    }

    public ResponseEntity<Map<String, Boolean>> deletePersonalInformation(Long id) {
        personalInformationRepository.deletePersonalInformationById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
