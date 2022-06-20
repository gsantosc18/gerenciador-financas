package com.davi.sintomasenfermagem.domain.enums;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

public enum TypeEnum {
    FIXED("Fixo"), DYNAMIC("Dinâmico");

    private String value;

    TypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static TypeEnum of(String value) {
        return Arrays.stream(values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
