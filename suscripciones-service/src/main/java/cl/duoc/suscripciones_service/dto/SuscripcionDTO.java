package cl.duoc.suscripciones_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SuscripcionDTO {

    private Long id;

    @NotBlank(message = "El tipo de plan es obligatorio")
    @Pattern(regexp = "^(BASICO|STANDARD|PREMIUM|MENSUAL|TRIMESTRAL|SEMESTRAL|ANUAL)$",
            message = "Tipo de plan no válido. Opciones: BASICO, STANDARD, PREMIUM, MENSUAL, TRIMESTRAL, SEMESTRAL, ANUAL")
    private String tipoPlan;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1000, message = "El monto mínimo es de $1.000")
    @Max(value = 1000000, message = "El monto máximo es de $1.000.000")
    private Integer monto;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(ACTIVA|INACTIVA|SUSPENDIDA|VENCIDA)$",
            message = "Estado no válido. Opciones: ACTIVA, INACTIVA, SUSPENDIDA, VENCIDA")
    private String estado;

    private UsuarioDTO usuario;
}
