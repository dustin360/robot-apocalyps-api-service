package com.apocalypse.robot.repo;

import com.apocalypse.robot.model.InfectionReport;
import com.apocalypse.robot.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 12 Mar, 2023
 **/
public interface InfectionReportRepo extends JpaRepository<InfectionReport, Long> {

    boolean existsByReportFiledByAndInfectedSurvivorAndStatus(Survivor filedBy, Survivor survivor,
                                                              InfectionReport.Status status);

    long countByInfectedSurvivorAndStatus(Survivor infectedSurvivor, InfectionReport.Status status);
}
