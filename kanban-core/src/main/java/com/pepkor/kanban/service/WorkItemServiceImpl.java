package com.pepkor.kanban.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.model.WorkItemDto;
import com.pepkor.kanban.entity.WorkItem;
import com.pepkor.kanban.mapper.EntityDtoMapper;
import com.pepkor.kanban.repository.WorkItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WorkItemServiceImpl implements WorkItemService {

    @Autowired
    WorkItemRepository workItemRepository;

    @Autowired
    EntityDtoMapper<WorkItemDto, WorkItem> entityDtoMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<WorkItemDto> getAllWorkItems() {
        List dtos = entityDtoMapper.convertToDtoList(workItemRepository.findAll(), WorkItemDto.class);
        return dtos;
    }

    @Override
    public List<WorkItemDto> getByStatus(Status status) {
        List<WorkItemDto> workItemDtos = entityDtoMapper.convertToDtoList(workItemRepository.findByStatus(status), WorkItemDto.class);
        return workItemDtos;
    }

    @Override
    public WorkItemDto getById(Long id) {
        WorkItemDto workItemDto = entityDtoMapper.convertToDto(workItemRepository.findById(id).get(), WorkItemDto.class);
        return workItemDto;
    }

    @Override
    public WorkItemDto save(WorkItemDto workItemDto) {
        WorkItem workItem = entityDtoMapper.convertToEntity(workItemDto, WorkItem.class);
        WorkItem savedWorkItem = workItemRepository.save(workItem);
        return entityDtoMapper.convertToDto(savedWorkItem, WorkItemDto.class);
    }

    @Override
    public WorkItemDto update(WorkItemDto workItemDto) throws JsonPatchException, JsonProcessingException {
        WorkItem savedWorkItem = workItemRepository.save(entityDtoMapper.convertToEntity(workItemDto, WorkItem.class));
        return entityDtoMapper.convertToDto(savedWorkItem, WorkItemDto.class);
    }

    @Override
    public WorkItemDto applyPatchToWorkItem(
            JsonPatch patch, WorkItemDto targetWorkItem) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetWorkItem, JsonNode.class));
        return objectMapper.treeToValue(patched, WorkItemDto.class);
    }
}
