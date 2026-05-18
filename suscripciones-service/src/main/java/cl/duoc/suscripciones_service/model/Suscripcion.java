package cl.duoc.suscripciones_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== TIPO DE PLAN =====
    @NotBlank(message = "El tipo de plan es obligatorio")
    @Size(min = 3, max = 50, message = "El tipo de plan debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
            message = "El tipo de plan solo puede contener letras y espacios")
    @Column(nullable = false, length = 50)
    private String tipoPlan;

    // ===== MONTO =====
    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1000, message = "El monto mínimo es de $1.000")
    @Max(value = 1000000, message = "El monto máximo es de $1.000.000")
    @Column(nullable = false)
    private Integer monto;

    // ===== FECHA DE INICIO =====
    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate fechaInicio;

    // ===== ESTADO =====
    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(ACTIVA|INACTIVA|SUSPENDIDA|VENCIDA)$",
            message = "El estado debe ser: ACTIVA, INACTIVA, SUSPENDIDA o VENCIDA")
    @Column(nullable = false, length = 20)
    private String estado;

    // ===== FK A OTRO MICROSERVICIO =====
    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    @Column(nullable = false)
    private Long usuarioId;
}