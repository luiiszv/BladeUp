package com.bladeUp.bladeUp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long service_id;

    @ManyToMany(mappedBy = "services")
    private Set<Barber> barbers;

    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Boolean status;
}
