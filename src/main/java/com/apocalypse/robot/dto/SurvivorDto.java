package com.apocalypse.robot.dto;

import com.apocalypse.robot.constants.DateTimeConstant;
import com.apocalypse.robot.enums.Gender;
import com.apocalypse.robot.model.SurvivorInventory;
import com.apocalypse.robot.model.embeddable.LastLocation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by David on 10 Mar, 2023
 **/
@Data
public class SurvivorDto {

    private Long id;

    private String name;

    private Integer age;

    private Gender gender;

    private LastLocation lastLocation;

    private Boolean infected;

    private List<SurvivorInventoryDto> inventoryList;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstant.DATE_TIME_PATTERN)
    private LocalDateTime createdAt;
}
