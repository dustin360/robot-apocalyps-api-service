package com.apocalypse.robot.model.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by David on 10 Mar, 2023
 **/
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class LastLocation {

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;
}
