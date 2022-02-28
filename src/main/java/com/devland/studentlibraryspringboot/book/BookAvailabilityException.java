package com.devland.studentlibraryspringboot.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book exist in the library")
public class BookAvailabilityException extends RuntimeException {

}
