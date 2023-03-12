package com.apocalypse.robot.model;

import com.apocalypse.robot.enums.Gender;
import com.apocalypse.robot.model.embeddable.LastLocation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by David on 10 Mar, 2023
 **/
@Entity
@Data
@Where(clause = "deleted = false")
public class Survivor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private LastLocation lastLocation;

    private Boolean deleted = false;

    private Boolean infected = false;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "survivor_id")
    private List<SurvivorInventory> inventoryList;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
