package com.pepkor.kanban.repository;

import com.pepkor.kanban.entity.WorkItem;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WorkItemSpecifications {

    public static Specification<WorkItem> getEmployeesByTitleSpec(String title) {
        return new Specification<WorkItem>() {
            @Override
            public Predicate toPredicate(Root<WorkItem> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("title"), title);
                return equalPredicate;
            }
        };
    }
}
