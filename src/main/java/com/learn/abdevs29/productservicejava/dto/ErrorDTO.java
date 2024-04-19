package com.learn.abdevs29.productservicejava.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    String message;
    String code;
}
