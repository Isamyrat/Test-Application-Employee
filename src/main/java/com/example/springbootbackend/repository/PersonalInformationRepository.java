package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation,Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from PERSONAL_INFORMATION  p where p.id = :id", nativeQuery = true)
    void deletePersonalInformationById(Long id);
}
