package com.pepkor.kanban.validator;

import com.pepkor.kanban.entity.WorkItem;
import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.repository.WorkItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class WorkItemValidator implements ConstraintValidator<MaxInProgress, Status> {

    protected int maxSize;

    @Autowired
    WorkItemRepository workItemRepository;

    @Override
    public void initialize(MaxInProgress maxInProgress) {
        maxSize = maxInProgress.maxValue();
    }

    @Override
    public boolean isValid(Status status, ConstraintValidatorContext constraintValidatorContext) {
        if (!isSupported(status)) {
            return true;
        }
        List<WorkItem> tasks = workItemRepository.findByStatus(Status.IN_PROGRESS);
        if (tasks == null || tasks.isEmpty()) {
            return true;
        }
        return tasks.size() < maxSize;
    }

    private boolean isSupported(Status status) {
        return status == Status.IN_PROGRESS;
    }
}


