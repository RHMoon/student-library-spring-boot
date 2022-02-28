package com.devland.studentlibraryspringboot.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such student exist with the provided id")

public class StudentNotFoundException extends RuntimeException {

}
