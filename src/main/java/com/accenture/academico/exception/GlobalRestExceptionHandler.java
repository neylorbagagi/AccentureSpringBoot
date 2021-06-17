package com.accenture.academico.exception;

import com.accenture.academico.model.APIErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RegisterNotFoundException.class})
    public ResponseEntity <APIErrorResponse> registerNotFound(RegisterNotFoundException ex, WebRequest request) {
        APIErrorResponse apiResponse = new APIErrorResponse
                .APIErrorResponseBuilder()
                .withDetail("Não foi possível encontrar o registro")
                .withMessage("ID requisitado inválido")
                .withError_code("404")
                .withStatus(HttpStatus.NOT_FOUND)
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity <APIErrorResponse> (apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> errorMsg= ex.getBindingResult().getFieldErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
        APIErrorResponse response = new APIErrorResponse.APIErrorResponseBuilder()
                .withStatus(status)
                .withDetail("Argumento inválido")
                .withMessage(errorMsg.toString())
                .withError_code("406")
                .withError_code(status.NOT_ACCEPTABLE.name())
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<>(response, response.getStatus());
    }

}
