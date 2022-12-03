package com.masai.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private String details;

}
