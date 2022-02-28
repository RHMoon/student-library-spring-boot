package com.devland.studentlibraryspringboot.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such book with the provided id")
public class BookNotFoundException extends RuntimeException{

}
