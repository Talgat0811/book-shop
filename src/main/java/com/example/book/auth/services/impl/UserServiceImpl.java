package com.example.book.auth.services.impl;

import com.example.book.auth.entities.User;
import com.example.book.auth.mappers.UserMapper;
import com.example.book.auth.models.FilterRequest;
import com.example.book.auth.models.UserModel;
import com.example.book.auth.repositories.UserRepository;
import com.example.book.auth.services.UserService;
import com.example.book.support.exceptions.NotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.book.support.utils.SpecificationHelper.concatFieldsAndLower;
import static com.example.book.support.utils.SpecificationHelper.getContainsLikePattern;
import static com.example.book.support.utils.StringUtils.isEmpty;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserModel> getAll(FilterRequest filterRequest, Pageable pageable) {
        return userRepository
                .findAll(new UserSpecification(filterRequest), pageable)
                .map(userMapper::toModel);
    }

    @Override
    public UserModel save(UserModel userModel) {
        User entity = userMapper.toEntity(userModel);
        entity.setPassword(passwordEncoder.encode(userModel.getPassword()));

        return userMapper.toModel(userRepository.save(entity));
    }

    @Override
    public UserModel update(UserModel userModel) {
        if (userModel.getId() == null)
            throw new IllegalArgumentException("ID is required for update operation");

        User updatedUser = userRepository.save(userMapper.toEntity(userModel));
        return userMapper.toModel(updatedUser);
    }

    @Override
    public boolean deleteById(Long id) throws NotFoundException {
        userRepository.delete(
                userRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("No user found with id '%s'.", id)))
        );
        return true;
    }

    @Override
    public UserModel findByLogin(String login) throws NotFoundException {
        return userRepository.findByLogin(login)
                .map(userMapper::toModel)
                .orElseThrow(() -> new NotFoundException(String.format("No user found with login '%s'.", login)));
    }

    @AllArgsConstructor
    static class UserSpecification implements Specification<User> {
        private final FilterRequest filterRequest;
        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            final List<Predicate> predicates = new ArrayList<>();

            if (!isEmpty(filterRequest.getSearchSubject())) {
                predicates.add(
                        criteriaBuilder.like(
                                concatFieldsAndLower(criteriaBuilder, root, null, "name", "lastname", "patronymic", "login"),
                                getContainsLikePattern(filterRequest.getSearchSubject())
                        )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
