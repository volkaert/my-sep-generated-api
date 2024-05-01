package fr.volkaert.sep.my_sep_generated_api.errors;

import fr.volkaert.sep.my_sep_generated_api.order.errors.OrderNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyRestControllerAdvice {

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class, DataIntegrityViolationException.class, HttpMessageNotReadableException.class, MissingRequestHeaderException.class, MethodArgumentTypeMismatchException.class})
    ResponseEntity<?> handle(Exception ex, HttpServletRequest httpRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        MyErrorResponseBody body = MyErrorResponseBody.build(httpStatus, getErrorCode(ex), ex.getMessage(), httpRequest);
        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    ResponseEntity<?> handle(OrderNotFoundException ex, HttpServletRequest httpRequest) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        MyErrorResponseBody body = MyErrorResponseBody.build(httpStatus, getErrorCode(ex), ex.getMessage(), httpRequest);
        return ResponseEntity.status(httpStatus).body(body);
    }

    private String getErrorCode(Exception ex) {
        String simpleClassName = ex.getClass().getSimpleName();
        String errorCode;

        if (simpleClassName.endsWith("Exception"))
            errorCode = simpleClassName.substring(0, simpleClassName.length() - "Exception".length());
        else
            errorCode = simpleClassName;
        return errorCode;
    }
}
