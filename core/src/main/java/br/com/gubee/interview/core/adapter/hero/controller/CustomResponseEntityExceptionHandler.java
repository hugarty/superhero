package br.com.gubee.interview.core.adapter.hero.controller;


import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

  @ExceptionHandler(value ={MethodArgumentNotValidException.class})
  public ResponseEntity<String> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception) {
    String result = exception.getAllErrors().stream()
      .map(i -> {
        String objectName = i.getObjectName();
        String field = ((DefaultMessageSourceResolvable) i.getArguments()[0]).getDefaultMessage();
        String errorMessage = i.getDefaultMessage();
        return objectName + "." + field + " - " + errorMessage;
      })
      .collect(Collectors.joining("\n"));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
  

  @ExceptionHandler(value ={DataIntegrityViolationException.class})
  public ResponseEntity<String> handleDataIntegrityViolationException (DataIntegrityViolationException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Violação de integridade detectada.\n\nTalvez esteja adicionando uma identificação que já existe.");
  }
  

}
