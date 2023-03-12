package com.apocalypse.robot.service.impl;

import com.apocalypse.robot.dto.PaginatedListDto;
import com.apocalypse.robot.dto.SurvivorCreationRequest;
import com.apocalypse.robot.dto.SurvivorDto;
import com.apocalypse.robot.dto.SurvivorPercentageDto;
import com.apocalypse.robot.exceptions.EntityAlreadyExistsException;
import com.apocalypse.robot.exceptions.NotFoundException;
import com.apocalypse.robot.mapper.SurvivorMapper;
import com.apocalypse.robot.model.Survivor;
import com.apocalypse.robot.model.embeddable.LastLocation;
import com.apocalypse.robot.repo.SurvivorRepo;
import com.apocalypse.robot.service.SurvivorService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by David on 10 Mar, 2023
 **/
@Service
@AllArgsConstructor
public class SurvivorServiceImpl implements SurvivorService {

    private SurvivorRepo survivorRepo;

    @Override
    public Survivor create(SurvivorCreationRequest request) {

        if (survivorRepo.existsByNameIgnoreCase(request.getName()))
            throw new EntityAlreadyExistsException("A survivor with this name already exists");

        Survivor survivor = SurvivorMapper.mapToEntity(request);

        return survivorRepo.save(survivor);
    }

    @Override
    public Survivor updateLocation(Long id, LastLocation location) {


        Survivor survivor = findById(id);
        survivor.setLastLocation(location);

        return survivorRepo.save(survivor);
    }

    @Override
    public PaginatedListDto<SurvivorDto> getAll(Boolean infected, int page, int limit) {

        Page<Survivor> pageInfo = survivorRepo.findAllByInfected(infected, PageRequest.of(page, limit));

        List<SurvivorDto> survivorDtoList = pageInfo.getContent().stream()
                .map(SurvivorMapper::mapToDto)
                .collect(Collectors.toList());

        return new PaginatedListDto<>(page, limit, pageInfo.getTotalElements(), survivorDtoList);
    }

    @Override
    public void flagAsInfected(Long id) {
        Survivor survivor = findById(id);
        if (!survivor.getInfected()) {
            survivor.setInfected(true);
            survivorRepo.save(survivor);
        }
    }


    @Override
    public Survivor findById(Long id) {
        return survivorRepo.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Survivor with ID %s was not found", id)));
    }

    @Override
    public SurvivorPercentageDto getSurvivorPercentageData() {

        var infectedSurvivors = survivorRepo.countByInfected(true);

        var unInfectedSurvivors = survivorRepo.countByInfected(false);

        var totalSurvivors = survivorRepo.count();

        SurvivorPercentageDto dto = new SurvivorPercentageDto();
        dto.setPercentageOfInfectedSurvivors(((double)infectedSurvivors / (double)totalSurvivors) * 100);
        dto.setPercentageOfUninfectedSurvivors(((double)unInfectedSurvivors / (double)totalSurvivors) * 100);

        return dto;
    }
}
