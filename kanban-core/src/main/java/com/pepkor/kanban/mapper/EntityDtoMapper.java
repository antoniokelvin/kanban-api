package com.pepkor.kanban.mapper;

import com.pepkor.kanban.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoMapper<S, T> {

    @Autowired
    private ModelMapper modelMapper;

    public S convertToDto(T user, Class<S> type) {
        return modelMapper.map(user, type);
    }

    public T convertToEntity(S user, Class<T> type) {
        return modelMapper.map(user, type);
    }

    public List<S> convertToDtoList(List<T> users, Class<S> type) {

        List<S> dtos = users
                .stream()
                .map(user -> modelMapper.map(user, type))
                .collect(Collectors.toList());
        return dtos;
    }

    public List<T> convertToEntityList(List<S> users, Class<T> type) {
        List<T> entities = users
                .stream()
                .map(user -> modelMapper.map(user, type))
                .collect(Collectors.toList());
        return entities;
    }
}
