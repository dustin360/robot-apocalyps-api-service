package com.apocalypse.robot.service.impl;

import com.apocalypse.robot.dto.InfectionReportRequest;
import com.apocalypse.robot.exceptions.EntityAlreadyExistsException;
import com.apocalypse.robot.model.InfectionReport;
import com.apocalypse.robot.model.Survivor;
import com.apocalypse.robot.repo.InfectionReportRepo;
import com.apocalypse.robot.service.InfectionReportService;
import com.apocalypse.robot.service.SurvivorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by David on 12 Mar, 2023
 **/
@Service
@AllArgsConstructor
@Slf4j
public class InfectionReportServiceImpl implements InfectionReportService {

    private InfectionReportRepo infectionReportRepo;

    private SurvivorService survivorService;

    @Override
    @Transactional
    public InfectionReport create(InfectionReportRequest request) {

        // validation
        Survivor reportingSurvivor = survivorService.findById(request.getReportingSurvivorId());

        Survivor infectedSurvivor = survivorService.findById(request.getInfectedSurvivorId());

        if (infectionReportRepo.existsByReportFiledByAndInfectedSurvivorAndStatus(reportingSurvivor, infectedSurvivor,
                InfectionReport.Status.VALID))
            throw new EntityAlreadyExistsException("A similar report has been previously filed by this survivor");

        // create report
        InfectionReport report = InfectionReport.builder()
                .comments(request.getComments())
                .status(InfectionReport.Status.VALID)
                .reportFiledBy(reportingSurvivor)
                .infectedSurvivor(infectedSurvivor)
                .deleted(false)
                .build();

        report =  infectionReportRepo.save(report);

        // if survivor has more than 3 reports registered, he should be flagged as 'infected'.
        if (infectionReportRepo.countByInfectedSurvivorAndStatus(
                infectedSurvivor, InfectionReport.Status.VALID) >= 3) {
            survivorService.flagAsInfected(infectedSurvivor.getId());
        }

        return report;
    }
}
