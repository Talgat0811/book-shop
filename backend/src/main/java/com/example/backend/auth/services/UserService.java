package com.example.backend.auth.services;

import com.example.backend.auth.entities.User;
import com.example.commons.models.FilterRequest;
import com.example.backend.auth.models.UserModel;
import com.example.commons.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserModel> getAll(FilterRequest filterRequest, Pageable pageable);

    UserModel save(UserModel userModel) throws NotFoundException;

    UserModel update(UserModel userModel) throws NotFoundException;

    boolean deleteById(Long id) throws NotFoundException;

    User findByLogin(String login) throws NotFoundException;

}
