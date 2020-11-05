package com.pepkor.kanban.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.pepkor.kanban.entity.WorkItem;
import com.pepkor.kanban.exceptions.ResourceNotFoundException;
import com.pepkor.kanban.mapper.EntityDtoMapper;
import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.model.WorkItemDto;
import com.pepkor.kanban.service.WorkItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("workitems")
public class WorkItemController {

    Logger logger = LoggerFactory.getLogger(WorkItemController.class);

    @Autowired
    private WorkItemService workItemService;

    @Autowired
    EntityDtoMapper<WorkItemDto, WorkItem> entityDtoMapper;

    @Autowired
    Validator validator;

    @GetMapping
    public ResponseEntity<List<WorkItemDto>> getAllWorkItems() {
        List<WorkItemDto> dtos = workItemService.getAllWorkItems();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<WorkItemDto>> getByStatus(@PathVariable Status status) {
        List<WorkItemDto> dtos = workItemService.getByStatus(status);
        if (dtos == null) {
            logger.error("Tasks not found");
            throw new ResourceNotFoundException("Tasks not found - id ");
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkItemDto> addWorkItem(@Valid @RequestBody WorkItemDto workItemDto) {
        WorkItemDto savedWorkItem = workItemService.save(workItemDto);
        return new ResponseEntity<>(savedWorkItem, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity patchWorkItem(@PathVariable Long id, @RequestBody JsonPatch jsonPatch) {
        WorkItemDto workItemDto = workItemService.getById(id);
        try {
            WorkItemDto patchedWorkItem = workItemService.applyPatchToWorkItem(jsonPatch, workItemDto);
            Set<ConstraintViolation<WorkItemDto>> constraintViolation = validator.validate(patchedWorkItem);
            if (!constraintViolation.isEmpty()) {
                throw new ConstraintViolationException(constraintViolation);
            } else {
                workItemService.update(patchedWorkItem);
            }
        } catch (JsonPatchException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity("Updated", HttpStatus.NO_CONTENT);
    }
}
