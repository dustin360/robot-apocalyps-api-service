package com.apocalypse.robot.service.impl;

import com.apocalypse.robot.dto.PaginatedListDto;
import com.apocalypse.robot.dto.SurvivorCreationRequest;
import com.apocalypse.robot.dto.SurvivorDto;
import com.apocalypse.robot.enums.Gender;
import com.apocalypse.robot.exceptions.EntityAlreadyExistsException;
import com.apocalypse.robot.model.Survivor;
import com.apocalypse.robot.model.embeddable.LastLocation;
import com.apocalypse.robot.repo.SurvivorRepo;
import com.apocalypse.robot.service.SurvivorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by David on 12 Mar, 2023
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SurvivorServiceImpl.class, ModelMapper.class})
class SurvivorServiceImplTest {

    @MockBean
    private SurvivorRepo survivorRepo;

    @Autowired
    private SurvivorService survivorService;

    private Survivor survivor;

    @BeforeEach
    void setUp() {
        survivor = new Survivor();
        survivor.setId(1L);
        survivor.setLastLocation(new LastLocation(1.33, 2.33));
        survivor.setInfected(false);
        survivor.setAge(40);
        survivor.setDeleted(false);
        survivor.setGender(Gender.MALE);
        survivor.setName("Peter Pan");
    }

    @Test
    void create_throwsException() {
        // data
        SurvivorCreationRequest request = new SurvivorCreationRequest();
        request.setAge(10);
        request.setGender(Gender.MALE);
        request.setLastLocation(new LastLocation(1.333, 4.5666));
        request.setName("Peter Pan");
        request.setInventoryItems(new ArrayList<>(Arrays.asList("Food", "Clothing")));

        when(survivorRepo.existsByNameIgnoreCase(anyString())).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class, ()->
                survivorService.create(request), "A survivor with this name already exists");

    }

    @Test
    void create() {
        // data
        SurvivorCreationRequest request = new SurvivorCreationRequest();
        request.setAge(10);
        request.setGender(Gender.MALE);
        request.setLastLocation(new LastLocation(1.333, 4.5666));
        request.setName("Peter Pan");
        request.setInventoryItems(new ArrayList<>(Arrays.asList("Food", "Clothing")));

        when(survivorRepo.existsByNameIgnoreCase(anyString())).thenReturn(false);

        survivorService.create(request);

        verify(survivorRepo, times(1)).save(any());

    }

    @Test
    void updateLocation() {
        LastLocation location = new LastLocation(4.55, 6.79);

        when(survivorRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(survivor));

        survivorService.updateLocation(1L, location);

        verify(survivorRepo, times(1)).save(any());

    }

    @Test
    void getAll() {

        Pageable pageable = PageRequest.of(1, 10);

        when(survivorRepo.findAllByInfected(true, pageable)).thenReturn(getPageableSurvivors());

        PaginatedListDto<SurvivorDto> result = survivorService.getAll(true, 1, 10);

        assertThat(result.getEntities()).hasSize(1);
        assertThat(result.getPage()).isEqualTo(1);
        assertThat(result.getLimit()).isEqualTo(10);

    }

    @Test
    void flagAsInfected() {
        when(survivorRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(survivor));

        survivorService.flagAsInfected(1L);

        verify(survivorRepo, times(1)).save(any());
    }


    private Page<Survivor> getPageableSurvivors() {
        return new PageImpl<Survivor>(Collections.singletonList(survivor));
    }
}