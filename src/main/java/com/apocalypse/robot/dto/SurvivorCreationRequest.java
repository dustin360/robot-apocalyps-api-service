package com.apocalypse.robot.dto;

import com.apocalypse.robot.enums.Gender;
import com.apocalypse.robot.model.embeddable.LastLocation;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SurvivorCreationRequest {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Valid
    @NotNull(message = "Last location is required")
    private LastLocation lastLocation;

    private List<String> inventoryItems;
}
