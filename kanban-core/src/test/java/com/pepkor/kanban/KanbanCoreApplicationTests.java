package com.pepkor.kanban;

import com.pepkor.kanban.entity.WorkItem;
import com.pepkor.kanban.model.Status;
import com.pepkor.kanban.model.WorkItemDto;
import com.pepkor.kanban.repository.WorkItemRepository;
import com.pepkor.kanban.validator.WorkItemValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = WorkItemValidator.class)
class KanbanCoreApplicationTests {

	@MockBean
	private WorkItemRepository mockRepository;
	@Autowired
	WorkItemValidator workItemValidator;
	@Autowired
	private ConfigurableApplicationContext applicationContext;
	LocalValidatorFactoryBean validator;

	@BeforeEach
	public void setup() {
		SpringConstraintValidatorFactory springConstraintValidatorFactory
				= new SpringConstraintValidatorFactory(applicationContext.getAutowireCapableBeanFactory());
		validator = new LocalValidatorFactoryBean();
		validator.setConstraintValidatorFactory(springConstraintValidatorFactory);
		validator.setApplicationContext(applicationContext);
		validator.afterPropertiesSet();
	}

	@Test
	void testMaxInProgressValidationReached() {
		List<WorkItem> entities = new ArrayList<>();
		entities.add(new WorkItem());
		entities.add(new WorkItem());
		entities.add(new WorkItem());
		entities.add(new WorkItem());
		entities.add(new WorkItem());
		entities.add(new WorkItem());
		Mockito.when(mockRepository.findByStatus(Status.IN_PROGRESS)).thenReturn(entities);
		WorkItemDto workItemDto = new WorkItemDto();
		workItemDto.setTitle("Test title");
		workItemDto.setStatus(Status.IN_PROGRESS);
		validator.validate(workItemDto);
		Set<ConstraintViolation<WorkItemDto>> constraintViolations = validator
				.validate(workItemDto);

		constraintViolations.stream().findAny();

		List<ConstraintViolation> result = constraintViolations.stream()                // convert list to stream
				.filter(constraint -> "Maximum in-progress tasks reached".equals(constraint.getMessage()))     // we dont like mkyong
				.collect(Collectors.toList());
		assertTrue(result.size() == 1);
	}

	@Test
	void testMaxInProgressValidation() {
		List<WorkItem> entities = new ArrayList<>();
		entities.add(new WorkItem());
		Mockito.when(mockRepository.findByStatus(Status.IN_PROGRESS)).thenReturn(entities);
		WorkItemDto workItemDto = new WorkItemDto();
		workItemDto.setTitle("Test title");
		workItemDto.setStatus(Status.IN_PROGRESS);
		validator.validate(workItemDto);
		Set<ConstraintViolation<WorkItemDto>> constraintViolations = validator
				.validate(workItemDto);

		constraintViolations.stream().findAny();

		List<ConstraintViolation> result = constraintViolations.stream()                // convert list to stream
				.filter(constraint -> "Maximum in-progress tasks reached".equals(constraint.getMessage()))     // we dont like mkyong
				.collect(Collectors.toList());
		assertTrue(result.size() == 0);
	}
}
