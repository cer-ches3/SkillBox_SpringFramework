package com.example.spring_jwt_auth_example.web.handler;

import com.example.spring_jwt_auth_example.exception.AlreadyExistsException;
import com.example.spring_jwt_auth_example.exception.EntityNotFoundException;
import com.example.spring_jwt_auth_example.exception.RefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class WebAppExceptionHandler {

    @ExceptionHandler(value = RefreshTokenException.class)
    public ResponseEntity<ErrorResponseBody> refreshTokenExceptionHandler(
            RefreshTokenException ex, WebRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, ex, request);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseBody> alreadyExistHandler(
            AlreadyExistsException ex, WebRequest request){
        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseBody> notFoundHandler(
            EntityNotFoundException ex, WebRequest request){
        return buildResponse(HttpStatus.NOT_FOUND, ex, request);
    }
    private ResponseEntity<ErrorResponseBody> buildResponse(
            HttpStatus httpStatus,
            Exception ex,
            WebRequest webRequest) {
        return ResponseEntity.status(httpStatus)
                .body(ErrorResponseBody.builder()
                        .message(ex.getMessage())
                        .description(webRequest.getDescription(false))
                        .build());
    }
}
