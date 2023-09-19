package com.example.commons.models;

import com.example.commons.enums.ResultCode;
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
