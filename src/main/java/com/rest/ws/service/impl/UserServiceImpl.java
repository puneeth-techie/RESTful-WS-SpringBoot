package com.rest.ws.service.impl;

import com.rest.ws.io.entity.UserEntity;
import com.rest.ws.repository.UserRepo;
import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.request.UserRequestModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserEntity userEntity;

    @Override
    public UserDto createUser(UserDto userDetails) {
        BeanUtils.copyProperties(userDetails, userEntity);
        userEntity.setEncryptedPassword("encryptTest1");
        userEntity.setUserId("test1UserId");
        UserEntity userEntityDetails = userRepo.save(userEntity);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntityDetails, userDto);
        return userDto;
    }
}
