package com.example.book.support.utils;

import jakarta.persistence.criteria.*;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class SpecificationHelper {

    public static String getContainsLikePattern(String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty() ? "%" : "%" + searchTerm.toLowerCase().trim() + "%";
    }

    public static Expression<String> concatFieldsAndLower(CriteriaBuilder criteriaBuilder, Root<?> root, Join<?, ?> join, String... fields) {
        List<Expression<String>> expressions = new ArrayList<>();
        Arrays.stream(fields).forEach(field -> expressions.add(criteriaBuilder.coalesce(join != null ? join.get(field) : root.get(field), " ")));

        return criteriaBuilder.lower(expressions.stream().reduce(criteriaBuilder::concat).get());
    }

}
