package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {

}
