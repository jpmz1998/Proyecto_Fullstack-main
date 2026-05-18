package cl.duoc.recomendacion_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RecomendacionDTO {
    private Long id;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El ID de la película es obligatorio")
    private Long idPelicula;

    @NotBlank(message = "El mensaje de recomendación no puede estar vacío")
    private String mensajePersonalizado;

    @Min(value = 0, message = "El nivel de confianza mínimo es 0")
    @Max(value = 100, message = "El nivel de confianza máximo es 100")
    private Integer nivelConfianza;

    @NotNull(message = "La fecha de recomendación es obligatoria")
    @PastOrPresent(message = "La fecha de recomendación no puede ser futura")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate fechaRecomendacion;

    @NotBlank(message = "El nombre de la película no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombrePelicula;
}
