package com.apocalypse.robot.service;

import com.apocalypse.robot.dto.InfectionReportRequest;
import com.apocalypse.robot.model.InfectionReport;

/**
 * Created by David on 12 Mar, 2023
 **/
public interface InfectionReportService {

    InfectionReport create(InfectionReportRequest request);
}
