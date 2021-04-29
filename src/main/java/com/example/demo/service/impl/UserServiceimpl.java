package com.example.demo.service.impl;

import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserRest creatUser(UserDetailsRequestModel user) {

        UserEntity userEntitye = userRepository.findByEmail(user.getEmail());
        if (userEntitye != null) throw new RuntimeException("user already exists");

        UserEntity userEntity = new UserEntity();

        String publicUserId = utils.generateUserId(30);

        userEntity.setUserId(publicUserId);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setEncryptedPassword(user.getPassword());

        UserEntity storedUserDetails = userRepository.save(userEntity);

        ModelMapper modelMapper = new ModelMapper();

        UserRest returnValue = modelMapper.map(storedUserDetails, UserRest.class);

        return returnValue;
    }

    @Override
    public UserRest getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserRest updateUser(String userId, UserDetailsRequestModel userDetailsRequestModel) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setFirstName(userDetailsRequestModel.getFirstName());
        userEntity.setLastName(userDetailsRequestModel.getLastName());
        UserEntity updatedUserDetails = userRepository.save(userEntity);
        ModelMapper modelMapper = new ModelMapper();
        UserRest returnValue = modelMapper.map(updatedUserDetails, UserRest.class);
        return returnValue;
    }

    @Override
    public List<UserRest> getUsers(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();
        List<UserRest> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (UserEntity userEntity : users){
            UserRest userRest = modelMapper.map(userEntity, UserRest.class);
            returnValue.add(userRest);
        }
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

//        if (categoryEntity == null)
//            throw new UserNotFoundException(categoryId);
        userRepository.delete(userEntity);
    }
}