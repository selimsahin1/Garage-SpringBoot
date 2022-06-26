package com.selimsahin.garage.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @Column(name = "slot")
    private List<Integer> slots;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private VehicleType vehicleType;

    @Column
    private Status status = Status.INGARAGE;

}
