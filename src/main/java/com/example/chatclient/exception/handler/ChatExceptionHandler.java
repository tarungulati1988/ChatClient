package com.example.chatclient.exception.handler;

import com.example.chatclient.exception.type.ApplicationException;
import com.example.chatclient.model.response.ErrorResponse;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@ControllerAdvice
public class ChatExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChatExceptionHandler.class);

  @Override
  protected ResponseEntity<Object>
  handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> validationList = Lists.newArrayList(ex.getLocalizedMessage());
    return ResponseEntity
        .badRequest()
        .body(ErrorResponse
            .builder()
            .message(validationList)
            .build());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {
    List<String> validationList = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .collect(Collectors.toList());
    LOGGER.info("Validation error list : " + validationList);
    return ResponseEntity
        .badRequest()
        .body(ErrorResponse
            .builder()
            .message(validationList)
            .build());
  }

  @ExceptionHandler(value = {ApplicationException.class})
  protected ResponseEntity<Object> handleApplicationException(ApplicationException ex,
                                                              WebRequest request) {
    return ResponseEntity
        .badRequest()
        .body(ErrorResponse
            .builder()
            .message(Lists.newArrayList(ex.getLocalizedMessage()))
            .build());
  }
}