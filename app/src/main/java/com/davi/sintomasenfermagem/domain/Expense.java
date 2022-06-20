package com.davi.sintomasenfermagem.domain;

import com.davi.sintomasenfermagem.domain.enums.TypeEnum;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense {
    private Long id;
    private double value;
    private String description;
    private TypeEnum type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
