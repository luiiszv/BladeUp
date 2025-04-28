package com.bladeUp.bladeUp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class Client extends User {

    private String address;
    private String phone;
}


