package com.apocalypse.robot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by David on 10 Mar, 2023
 **/
@Entity
@Data
public class SurvivorInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "survivor_id")
    private Survivor survivor;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
