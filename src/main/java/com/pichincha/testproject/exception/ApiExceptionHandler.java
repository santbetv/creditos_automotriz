package com.pichincha.testproject.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.UnknownHostException;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final String CODIGO_ERROR = "001";
    private static final String CODIGO_ERROR_VALIDATION = "002";
    private static final String ERROR_VALIDACION = "Error de validación";
    private static final String ERROR_VALIDACION_NO_EXISTE = "Error de validación, cliente no existe";


    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ApiExeceptionResponse> handleUnknownHostException(UnknownHostException ex) {
        ApiExeceptionResponse response = new ApiExeceptionResponse("", "Error de conexion", "erorr-1024", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiExeceptionResponse response = new ApiExeceptionResponse(ex.getLocalizedMessage(), ERROR_VALIDACION, CODIGO_ERROR,"Error en la base de datos: Violación de restricción de columna única.");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BussinesRuleException.class)
    public ResponseEntity<ApiExeceptionResponse> handleBussinesRuleException(BussinesRuleException ex) {
        ApiExeceptionResponse response = new ApiExeceptionResponse(ex.getType(), ERROR_VALIDACION, CODIGO_ERROR, ex.getLocalizedMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

}
