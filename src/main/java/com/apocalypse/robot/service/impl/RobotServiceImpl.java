package com.apocalypse.robot.service.impl;

import com.apocalypse.robot.dto.RobotDto;
import com.apocalypse.robot.exceptions.NotFoundException;
import com.apocalypse.robot.service.RobotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David on 12 Mar, 2023
 **/
@Service
@AllArgsConstructor
@Slf4j
public class RobotServiceImpl implements RobotService {

    private ObjectMapper mapper = new ObjectMapper();

    private RestTemplate restTemplate;

    @Override
    public List<RobotDto> getAll() throws JsonProcessingException {

        String url =  "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Authorization", "Basic XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

            log.info("robot details: {}", mapper.writeValueAsString(response.getBody()));

            // get json output
            String output= new Gson().toJson(response.getBody());
            JsonArray jsonArray = (JsonArray) JsonParser.parseString(output);

            RobotDto[] robotsArray = mapper.readValue(jsonArray.toString(), RobotDto[].class);


            return Arrays.asList(robotsArray);

        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Robots not found");
        }
    }
}
