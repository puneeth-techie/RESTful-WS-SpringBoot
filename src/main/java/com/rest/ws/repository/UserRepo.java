package com.rest.ws.repository;

import com.rest.ws.io.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<UserEntity, Long> {
    /**
     * Finding the User by their email.
     * @param email
     * @return
     */
    UserEntity findByEmail(String email);

    /**
     * Finding the user by their userId.
     * @param id
     * @return
     */
    UserEntity findByUserId(String id);
}
