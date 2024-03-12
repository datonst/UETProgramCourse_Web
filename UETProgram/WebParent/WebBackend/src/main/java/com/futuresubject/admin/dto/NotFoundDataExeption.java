package com.futuresubject.admin.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Found")
public class NotFoundDataExeption extends Exception {
    public NotFoundDataExeption(String message) {
        super(message);
    }
}
