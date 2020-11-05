package com.pepkor.kanban.service;

import com.pepkor.kanban.entity.User;
import com.pepkor.kanban.mapper.EntityDtoMapper;
import com.pepkor.kanban.model.UserDto;
import com.pepkor.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityDtoMapper<UserDto, User> userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return  userMapper.convertToDtoList(userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return userMapper.convertToDto(userOptional.get(), UserDto.class);
        return null;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User userEntity = userMapper.convertToEntity(userDto, User.class);
        User savedUser = userRepository.save(userEntity);
        return userMapper.convertToDto(savedUser, UserDto.class);
    }
}
