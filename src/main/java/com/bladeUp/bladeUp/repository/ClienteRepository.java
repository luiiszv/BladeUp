package com.bladeUp.bladeUp.repository;

import com.bladeUp.bladeUp.model.Client;
import com.bladeUp.bladeUp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {




    //kee
}