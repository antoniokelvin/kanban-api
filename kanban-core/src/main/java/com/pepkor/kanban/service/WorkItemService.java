package com.pepkor.kanban.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.entity.WorkItem;
import com.pepkor.kanban.model.WorkItemDto;

import java.util.List;
import java.util.Map;

public interface WorkItemService {

    List<WorkItemDto> getAllWorkItems();

    List<WorkItemDto> getByStatus(Status status);

    WorkItemDto getById(Long id);

    WorkItemDto save(WorkItemDto workItemDto);

    WorkItemDto update(WorkItemDto workItemDto) throws JsonPatchException, JsonProcessingException;

    WorkItemDto applyPatchToWorkItem(
            JsonPatch patch, WorkItemDto targetWorkItem) throws JsonPatchException, JsonProcessingException;

}
