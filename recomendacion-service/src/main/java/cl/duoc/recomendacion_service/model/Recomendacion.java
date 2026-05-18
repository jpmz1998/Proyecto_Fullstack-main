package cl.duoc.recomendacion_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    @Column(nullable = false)
    private Long idUsuario;

    @NotNull(message = "El ID de la película es obligatorio")
    @Positive(message = "El ID de la película debe ser un número positivo")
    @Column(nullable = false)
    private Long idPelicula;

    // ===== DATOS DE LA RECOMENDACIÓN =====

    @Size(min = 10, max = 500, message = "El mensaje personalizado debe tener entre 10 y 500 caracteres")
    @Column(length = 500)
    private String mensajePersonalizado;  // Opcional, pero con límite

    @NotNull(message = "El nivel de confianza es obligatorio")
    @Min(value = 0, message = "El nivel de confianza no puede ser menor a 0%")
    @Max(value = 100, message = "El nivel de confianza no puede ser mayor a 100%")
    @Column(nullable = false)
    private Integer nivelConfianza;  // Porcentaje (0-100)

    @NotNull(message = "La fecha de recomendación es obligatoria")
    @PastOrPresent(message = "La fecha de recomendación no puede ser futura")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate fechaRecomendacion;
}
