package com.apocalypse.robot.service;

import com.apocalypse.robot.dto.RobotDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by David on 12 Mar, 2023
 **/
public interface RobotService {

    List<RobotDto> getAll() throws JsonProcessingException;
}
