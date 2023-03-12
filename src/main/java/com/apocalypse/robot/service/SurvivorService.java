package com.apocalypse.robot.service;

import com.apocalypse.robot.dto.PaginatedListDto;
import com.apocalypse.robot.dto.SurvivorCreationRequest;
import com.apocalypse.robot.dto.SurvivorDto;
import com.apocalypse.robot.dto.SurvivorPercentageDto;
import com.apocalypse.robot.model.Survivor;
import com.apocalypse.robot.model.embeddable.LastLocation;

import java.util.List;

/**
 * Created by David on 10 Mar, 2023
 **/
public interface SurvivorService {

    Survivor create(SurvivorCreationRequest request);

    Survivor updateLocation(Long id, LastLocation location);

    PaginatedListDto<SurvivorDto> getAll(Boolean infected, int page, int limit);

    void flagAsInfected(Long id);

    Survivor findById(Long id);

    SurvivorPercentageDto getSurvivorPercentageData();
}
