package cl.duoc.calificacion_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CalificacionDTO {

    @NotNull(message = "Los puntos no pueden estar vacíos")
    @Min(value = 1, message = "La calificación mínima es 1 estrella")
    @Max(value = 7, message = "La calificación máxima es 7 estrellas")
    private Integer puntos;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCalificacion;

    @NotNull(message = "Debe indicar el usuario")
    private Long idUsuario;

    @NotNull(message = "Debe indicar la película")
    private Long idPelicula;
}
