package com.ugurukku.linkshortener.exception;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.AUTH_ERROR;
import static com.ugurukku.linkshortener.model.constants.ErrorMessages.UNHANDLED_ERROR;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GeneralExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GeneralResponse<Void>> handleNotFound(NotFoundException e, Locale locale) {
        String message = getLocale(e, locale);
        log.warn("NOT FOUND EXCEPTION : {}", message);
        return new ResponseEntity<>(new GeneralResponse<>(404, message), NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GeneralResponse<Void>> handleAccessDenied(AccessDeniedException e, Locale locale){
        String message = getLocale(e, locale);
        log.warn("ACCESS DENIED EXCEPTION : {}",message);
        return new ResponseEntity<>(new GeneralResponse<>(403,message),FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GeneralResponse<Void>> handleBadRequest(BadRequestException e,Locale locale){
        String message = getLocale(e,locale);
        log.warn("BAD REQUEST EXCEPTION : {}",message);
        return new ResponseEntity<>(new GeneralResponse<>(400,message),BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse<Void>> handleUnexpected(Exception e, Locale locale){
        String message = getLocale(UNHANDLED_ERROR,locale);
        log.warn("UNEXPECTED ERROR OCCURRED : {}", formatExceptionMessage(e));
        return new ResponseEntity<>(new GeneralResponse<>(500,message),INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationError.class)
    public ResponseEntity<GeneralResponse<Void>> handleUnexpected(AuthenticationError e, Locale locale){
        String message = getLocale(AUTH_ERROR,locale);
        log.warn("AUTHENTICATION ERROR OCCURRED : {}", e.getMessage());
        return new ResponseEntity<>(new GeneralResponse<>(403,message),FORBIDDEN);
    }

    private String getLocale(Exception e, Locale locale) {
        return messageSource.getMessage(e.getMessage(),null, locale);
    }

    private String getLocale(String message,Locale locale) {
        return messageSource.getMessage(message,null, locale);
    }

    private String formatExceptionMessage(Exception exception) {
        return String.format("%s with message : { %s } at : { %s }",exception.getClass().getName(), exception.getMessage(), exception.getStackTrace()[0]);
    }

}
