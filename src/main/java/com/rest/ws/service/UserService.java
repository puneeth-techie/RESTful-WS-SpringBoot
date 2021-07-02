package com.rest.ws.service;

import com.rest.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * Creating or Registering a new User.
     * @param userDto
     * @return
     */
    UserDto createUser(UserDto userDto);

    /**
     * Fetching user by their email.
     * @param email
     * @return
     */
    UserDto getUser(String email);

    /**
     * Fetching user by their userId.
     * @param userId
     * @return
     */
    UserDto getUserById (String userId);

    /**
     * Deleting a specific user by their userId.
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * Fetching all the users as list of users.
     * @param page
     * @param limit
     * @return
     */
    List<UserDto> getAllUser(int page, int limit);
    
    boolean verifyEmailToken(String token);
}
