package com.autodrivers.motors.dto.conversor;

public record TasaCambio(
    String monedaBase,
    String monedaDestino,
    double tasaConversion,
    double resultadoConversion
) {

    public TasaCambio(Conversion conversion){
        this(conversion.base_code(), conversion.target_code(),
                conversion.conversion_rate(), conversion.conversion_result());
    }
}
