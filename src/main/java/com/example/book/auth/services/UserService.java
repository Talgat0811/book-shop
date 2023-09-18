package com.example.book.auth.services;

import com.example.book.auth.models.FilterRequest;
import com.example.book.auth.models.UserModel;
import com.example.book.support.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserModel> getAll(FilterRequest filterRequest, Pageable pageable);

    UserModel save(UserModel userModel);

    UserModel update(UserModel userModel);

    boolean deleteById(Long id) throws NotFoundException;

    UserModel findByLogin(String login) throws NotFoundException;

}
