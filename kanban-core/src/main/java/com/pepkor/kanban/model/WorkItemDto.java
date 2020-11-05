package com.pepkor.kanban.model;


import javax.validation.constraints.NotNull;

import com.pepkor.kanban.validator.MaxInProgress;

public class WorkItemDto {

    private Long id;
    @NotNull
    private String title;
    private String description;
    private UserDto user;
    @MaxInProgress(maxValue = 5)
    private Status status;

    public WorkItemDto() {
        user = new UserDto();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
