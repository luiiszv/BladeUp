package com.bladeUp.bladeUp.repository;

import com.bladeUp.bladeUp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {


    //kee
}