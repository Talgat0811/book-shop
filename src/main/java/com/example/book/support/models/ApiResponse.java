package com.example.book.support.models;

import com.example.book.support.enums.ResultCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> implements Serializable {
    T result;
    ResultCode resultCode;
    String details;

    public ApiResponse(T result, ResultCode resultCode) {
        this.result = result;
        this.resultCode = resultCode;
    }
}
