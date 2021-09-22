package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from Address  a where a.id = :id", nativeQuery = true)
    void deleteAddressById(Long id);
}
