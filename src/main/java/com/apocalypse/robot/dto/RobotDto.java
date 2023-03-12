package com.apocalypse.robot.dto;

import lombok.Data;

/**
 * Created by David on 12 Mar, 2023
 **/
@Data
public class RobotDto {

    private String model;

    private String serialNumber;

    private String manufacturedDate;

    private String category;
}
