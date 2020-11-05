package com.pepkor.kanban.config;


import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.validator.MaxInProgress;
import com.pepkor.kanban.validator.WorkItemValidator;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintValidator;
import javax.validation.Valid;
import javax.validation.Validator;

@TestConfiguration
public class TestConfig {

    @Bean
    public ConstraintValidator<MaxInProgress, Status> modelMapper() {
        return new WorkItemValidator();
    }

}
