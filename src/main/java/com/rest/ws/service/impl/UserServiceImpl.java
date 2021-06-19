package com.rest.ws.service.impl;

import com.rest.ws.io.entity.UserEntity;
import com.rest.ws.repository.UserRepo;
import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@ComponentScan({"com.rest.ws.io.entity", "com.rest.ws.ui.shared"})
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserEntity userEntity;

    @Autowired
    UserDto userDto;

    @Override
    public UserDto createUser(UserDto userDetails) {
        //UserEntity userEntity = new UserEntity();
        /**
         * Checking if the use is already exist with the given email or not.
         */
        UserEntity isEmailPresent = userRepo.findByEmail(userDetails.getEmail());
        if(isEmailPresent != null) throw new RuntimeException("Email already exist.");

        /**
         * Copying the UserDto properties to UserEntity.
         */
        BeanUtils.copyProperties(userDetails, userEntity);
        /**
         * Setting the other properties internally.
         */
        String uuid = UUID.randomUUID().toString();
        userEntity.setEncryptedPassword("encryptTest1");
        userEntity.setUserId(uuid);
        /**
         * Saving the USer Information to the DB
         */
        UserEntity userEntityDetails = userRepo.save(userEntity);
        /**
         * Copying the Saved UserEntity to UserDto
         */
        BeanUtils.copyProperties(userEntityDetails, userDto);
        return userDto;
    }

    @Override
    public UserDto getUser(String userId) {
        UserEntity user = userRepo.findByUserId(userId);
        if(user != null){
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }else {
            throw new RuntimeException("User with the given ID not exist.");
        }
    }
}
