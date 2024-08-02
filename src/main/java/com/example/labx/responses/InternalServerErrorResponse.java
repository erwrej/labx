package com.example.labx.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InternalServerErrorResponse {
    private Integer status;
    private String reason;
}
