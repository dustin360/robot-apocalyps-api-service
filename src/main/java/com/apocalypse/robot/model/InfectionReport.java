package com.apocalypse.robot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by David on 10 Mar, 2023
 **/
@Entity
@Data
@Where(clause = "deleted = false")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfectionReport {

    public enum Status{VALID, INVALID}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String comments;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Survivor reportFiledBy;

    @ManyToOne
    private Survivor infectedSurvivor;


    private Boolean deleted = false;
}
