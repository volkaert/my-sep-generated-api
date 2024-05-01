package fr.volkaert.sep.my_sep_generated_api.errors;

import fr.volkaert.sep.my_sep_generated_api.order.errors.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyRestControllerAdvice {

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class, DataIntegrityViolationException.class, HttpMessageNotReadableException.class, MissingRequestHeaderException.class, MethodArgumentTypeMismatchException.class})
    ResponseEntity<MyErrorResponseBody> handleBadRequest(Exception ex, HttpServletRequest httpRequest) {
        return handle(HttpStatus.BAD_REQUEST, ex, httpRequest);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    ResponseEntity<MyErrorResponseBody> handleNotFound(OrderNotFoundException ex, HttpServletRequest httpRequest) {
        return handle(HttpStatus.NOT_FOUND, ex, httpRequest);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<MyErrorResponseBody> handleInternalServerError(Exception ex, HttpServletRequest httpRequest) {
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, ex, httpRequest);
    }

    private ResponseEntity<MyErrorResponseBody> handle(HttpStatus httpStatus, Exception ex, HttpServletRequest httpRequest) {
        String errorCode = getErrorCode(ex);
        String errorMessage = ex.getMessage();
        log.error("ERROR: httpStatus: {}, code: {}, message: {}", httpStatus, errorCode, errorMessage, ex);
        MyErrorResponseBody body = MyErrorResponseBody.build(httpStatus, errorCode, errorMessage, httpRequest);
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
