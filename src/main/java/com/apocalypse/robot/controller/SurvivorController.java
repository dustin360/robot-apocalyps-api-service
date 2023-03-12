package com.apocalypse.robot.controller;

import com.apocalypse.robot.constants.ApiRoute;
import com.apocalypse.robot.dto.InfectionReportRequest;
import com.apocalypse.robot.dto.GeneralResponse;
import com.apocalypse.robot.dto.SurvivorCreationRequest;
import com.apocalypse.robot.mapper.SurvivorMapper;
import com.apocalypse.robot.model.Survivor;
import com.apocalypse.robot.model.embeddable.LastLocation;
import com.apocalypse.robot.repo.InfectionReportRepo;
import com.apocalypse.robot.service.InfectionReportService;
import com.apocalypse.robot.service.SurvivorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by David on 10 Mar, 2023
 **/
@RestController
@AllArgsConstructor
@RequestMapping(value = ApiRoute.V1 + ApiRoute.SURVIVORS)
@Api(value = " Set of endpoints for Survivor Management")
public class SurvivorController {

    private SurvivorService survivorService;

    private InfectionReportService infectionReportService;

    /**
     * Endpoint for creating a survivor. Its important to keep the name of the survivor unique
     */
    @PostMapping
    @ApiOperation(value = "Endpoint for creating a survivor. Its important to keep the name of the survivor unique")
    public ResponseEntity<?> createSurvivor(@RequestBody @Valid SurvivorCreationRequest request) {

        Survivor survivor = survivorService.create(request);

        return new ResponseEntity<>(
                new GeneralResponse(
                        HttpStatus.CREATED.value(),
                        "Survivor created", SurvivorMapper.mapToDto(survivor)),
                HttpStatus.CREATED);
    }

    /**
     *
     * Endpoint to update survivor location
     */
    @PatchMapping(value = "/{survivorId}")
    @ApiOperation(value = "Endpoint to update survivor location")
    public ResponseEntity<?> updateSurvivorLocation(@PathVariable("survivorId") long id,
                                                    @RequestBody @Valid LastLocation location) {

        survivorService.updateLocation(id, location);

        return new ResponseEntity<>(
                new GeneralResponse(
                        HttpStatus.OK.value(),
                        "Last location updated"),
                HttpStatus.OK);
    }


    /**
     *
     * Endpoint to register an infection report about a survivor. A survivor can only register " +
     * one inspection report for another survivor
     */
    @PostMapping(value = "/infection-report")
    @ApiOperation(value = "Endpoint to register an infection report about a survivor. A survivor can only register " +
            "one inspection report for another survivor")
    public ResponseEntity<?> registerInfectionReport(@RequestBody @Valid InfectionReportRequest request) {

        infectionReportService.create(request);

        return new ResponseEntity<>(
                new GeneralResponse(
                        HttpStatus.CREATED.value(),
                        "Contamination report saved"),
                HttpStatus.CREATED);
    }
}
