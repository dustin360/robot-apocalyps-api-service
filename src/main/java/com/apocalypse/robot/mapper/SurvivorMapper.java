package com.apocalypse.robot.mapper;

import com.apocalypse.robot.dto.SurvivorCreationRequest;
import com.apocalypse.robot.dto.SurvivorDto;
import com.apocalypse.robot.dto.SurvivorInventoryDto;
import com.apocalypse.robot.model.Survivor;
import com.apocalypse.robot.model.SurvivorInventory;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by David on 10 Mar, 2023
 **/
public class SurvivorMapper {

    public static Survivor mapToEntity(SurvivorCreationRequest request) {

        if (request == null)
            return null;

        ModelMapper mapper = new ModelMapper();

        Survivor survivor = new Survivor();
        mapper.map(request, survivor);

        List<SurvivorInventory> inventoryList = new ArrayList<>();
        if (request.getInventoryItems() != null) {
            request.getInventoryItems().forEach(i -> {
                SurvivorInventory inventory = new SurvivorInventory();
                inventory.setName(i);
                inventoryList.add(inventory);
            });
        }

        survivor.setInventoryList(inventoryList);
        return survivor;
    }


    public static SurvivorDto mapToDto(Survivor entity) {
        if (entity == null)
            return null;

        ModelMapper mapper = new ModelMapper();

        SurvivorDto dto = mapper.map(entity, SurvivorDto.class);
        dto.setInventoryList(mapToDtoList(entity.getInventoryList()));
        return dto;
    }


    public static SurvivorInventoryDto mapToDto(SurvivorInventory entity) {
        if (entity == null)
            return null;

        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, SurvivorInventoryDto.class);
    }

    public static List<SurvivorInventoryDto> mapToDtoList(List<SurvivorInventory> entityList) {
        if (entityList == null || entityList.isEmpty())
            return Collections.emptyList();

        return entityList.stream()
                .map(SurvivorMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
