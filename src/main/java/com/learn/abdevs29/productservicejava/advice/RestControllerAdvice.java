package com.learn.abdevs29.productservicejava.advice;

import com.learn.abdevs29.productservicejava.dto.ErrorDTO;
import com.learn.abdevs29.productservicejava.exception.CategoryNotFoundException;
import com.learn.abdevs29.productservicejava.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException (ProductNotFoundException exception) {
        ErrorDTO dto = new ErrorDTO();
        dto.setCode("102");
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCategoryNotFoundException (CategoryNotFoundException exception) {
        ErrorDTO dto = new ErrorDTO();
        dto.setCode("102");
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
