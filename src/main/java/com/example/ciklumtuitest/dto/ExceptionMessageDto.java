package com.example.ciklumtuitest.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Exception representation
 */
@Value
@AllArgsConstructor
public class ExceptionMessageDto {

    /**
     * Status code of response
     */
    int status;

    /**
     * Reason why exception happened
     */
    String message;

}
