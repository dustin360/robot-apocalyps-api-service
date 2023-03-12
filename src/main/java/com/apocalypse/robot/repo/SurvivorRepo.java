package com.apocalypse.robot.repo;

import com.apocalypse.robot.model.Survivor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 10 Mar, 2023
 **/
public interface SurvivorRepo extends JpaRepository<Survivor, Long> {

    Page<Survivor> findAllByInfected(Boolean infected, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);

    long countByInfected(Boolean infected);
}
