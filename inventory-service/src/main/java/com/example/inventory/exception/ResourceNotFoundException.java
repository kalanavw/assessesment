package com.example.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Copyright (c) 2021 All Rights Reserved
 * Created by Kalana Weerarathne on 26/06/2021 12:47 PM.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
