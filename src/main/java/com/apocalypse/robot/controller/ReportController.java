package com.apocalypse.robot.controller;

import com.apocalypse.robot.constants.ApiRoute;
import com.apocalypse.robot.dto.GeneralResponse;
import com.apocalypse.robot.model.embeddable.LastLocation;
import com.apocalypse.robot.service.RobotService;
import com.apocalypse.robot.service.SurvivorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by David on 12 Mar, 2023
 **/
@RestController
@RequestMapping(value = ApiRoute.V1 + ApiRoute.REPORTS)
@AllArgsConstructor
@Api(value = " Set of endpoints for Reports")
public class ReportController {

    private RobotService robotService;

    private SurvivorService survivorService;

    /**
     *
     * Endpoint to get all Robots
     */
    @GetMapping(value = ApiRoute.ROBOTS)
    @ApiOperation(value = "Endpoint to get all Robots")
    public ResponseEntity<?> getAllRobots() throws JsonProcessingException {

        return new ResponseEntity<>(
                new GeneralResponse(
                        HttpStatus.OK.value(),
                        "",
                        robotService.getAll()),
                HttpStatus.OK);
    }

    /**
     * Endpoint to get all survivors. You can filter by 'infected' and 'uninfected'. Results are paginated
     *
     * @param infected
     * @param page
     * @param limit
     * @return
     * @throws JsonProcessingException
     */
    @ApiOperation(value = "Endpoint to get all survivors. You can filter by 'infected' and 'uninfected'. Results are" +
            " paginated")
    @GetMapping(value = ApiRoute.SURVIVORS)
    public ResponseEntity<?> getAllSurvivors(@RequestParam(defaultValue = "true") Boolean infected,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int limit) throws JsonProcessingException {

        return new ResponseEntity<>(
                new GeneralResponse(
                        HttpStatus.OK.value(),
                        "",
                        survivorService.getAll(infected, page, limit)),
                HttpStatus.OK);
    }

    /**
     *
     * Endpoint to get infection stats - percentage of infected and uninfected survivors
     */
    @ApiOperation(value = "Endpoint to get infection stats - percentage of infected and uninfected survivors")
    @GetMapping(value = ApiRoute.INFECTION_STATS)
    public ResponseEntity<?> getInfectionStats() throws JsonProcessingException {

        return new ResponseEntity<>(
                new GeneralResponse(
                        HttpStatus.OK.value(),
                        "",
                        survivorService.getSurvivorPercentageData()),
                HttpStatus.OK);
    }


}
