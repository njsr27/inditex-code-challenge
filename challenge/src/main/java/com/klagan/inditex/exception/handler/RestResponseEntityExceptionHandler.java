package com.klagan.inditex.exception.handler;

import com.klagan.inditex.controller.response.CustomErrorResponseBody;
import com.klagan.inditex.exception.InvalidRequestException;
import com.klagan.inditex.exception.PricesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

import static com.klagan.inditex.constants.DateConstants.ACCEPTED_DATE_FORMAT;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<CustomErrorResponseBody> handleDateTimeParseException(DateTimeParseException ex, WebRequest webRequest) {
        return ResponseEntity.badRequest()
            .body(
                CustomErrorResponseBody.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message("There was an error with the date, try to use the format: " + ACCEPTED_DATE_FORMAT)
                    .path(((ServletWebRequest) webRequest).getRequest().getRequestURI())
                    .build()
            );
    }

    @ExceptionHandler({PricesNotFoundException.class})
    public ResponseEntity<CustomErrorResponseBody> handlePricesNotFoundException(PricesNotFoundException ex, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                CustomErrorResponseBody.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(ex.getMessage())
                    .path(((ServletWebRequest) webRequest).getRequest().getRequestURI())
                    .build()
            );
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<CustomErrorResponseBody> handleInvalidRequestException(InvalidRequestException ex, WebRequest webRequest) {
        return ResponseEntity.badRequest()
            .body(
                CustomErrorResponseBody.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(ex.getMessage())
                    .path(((ServletWebRequest) webRequest).getRequest().getRequestURI())
                    .build()
            );
    }

}
