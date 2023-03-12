package com.apocalypse.robot.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class InfectionReportRequest {

    @NotNull(message = "Reporting survivor ID is required")
    private Long reportingSurvivorId;

    @NotNull(message = "Infected survivor ID is required")
    private Long infectedSurvivorId;

    @Size(max = 500, message = "Comments should not exceed 500 characters")
    @NotEmpty(message = "Comments are required to file this report")
    private String comments;


}
