package com.apocalypse.robot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by David on 12 Mar, 2023
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurvivorPercentageDto {

    private Double percentageOfInfectedSurvivors;

    private Double percentageOfUninfectedSurvivors;
}
