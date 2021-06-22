package com.rest.ws.service.impl;

import com.rest.ws.exception.UserServiceException;
import com.rest.ws.io.entity.UserEntity;
import com.rest.ws.repository.UserRepo;
import com.rest.ws.service.UserService;
import com.rest.ws.shared.dto.UserDto;
import com.rest.ws.ui.model.response.ErrorMessages;
import com.rest.ws.ui.model.response.OperationResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Creating or Registering a new user.
     * @param userDetails
     * @return
     */
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
        /**
         * Encoding User Password using BcryptPasswordEncoder
         */
        String encodedPassword = bCryptPasswordEncoder.encode(userDetails.getPassword());
        userEntity.setEncryptedPassword(encodedPassword);
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

    /**
     * Fetching user by their userId.
     * @param userId
     * @return
     */
    @Override
    public UserDto getUserById(String userId) {
        UserEntity user = userRepo.findByUserId(userId);
        if (user == null)
            throw new RuntimeException("User with the given ID not found.");
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    /**
     * Deleting a specific user by their userId.
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {
        UserEntity userPresent = userRepo.findByUserId(userId);
        if(userPresent == null) throw new UserServiceException(ErrorMessages.NO_USER_ID_FOUND);
        userRepo.delete(userPresent);
    }

    /**
     * Fetching the all users as list of users.
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<UserDto> getAllUser(int page, int limit) {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        Pageable userPage = PageRequest.of(page, limit);
        Page<UserEntity> allUsers = userRepo.findAll(userPage);
        List<UserEntity> usersList = allUsers.getContent();
        for (UserEntity userEntity: usersList){
            BeanUtils.copyProperties(userEntity, userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    /**
     * Fetching user by their email.
     * @param email
     * @return
     */
    @Override
    public UserDto getUser(String email) {
        UserEntity user = userRepo.findByEmail(email);
        if(user != null){
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }else {
            throw new UsernameNotFoundException("The the given email id not found.");
        }
    }

    /**
     * Loading the valid user to Spring security User credentials
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userPresent = userRepo.findByEmail(email);
        if(userPresent == null) throw new UsernameNotFoundException("The the given email id not found.");
        return new User(userPresent.getEmail(), userPresent.getEncryptedPassword(), new ArrayList<>());
    }
}
