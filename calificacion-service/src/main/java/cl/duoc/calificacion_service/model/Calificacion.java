package cl.duoc.calificacion_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacion;

    @Min(1)
    @Max(7)
    @NotNull(message = "no pueed estar vacio los puntos")
    private Integer puntos;

    @NotNull(message = "no puede ester vacia la fecha de calificacion")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCalificacion;

    // Enlaces logicos a otros microservicios
    @NotNull( message = "la id_usuario no puede ser nula")
    private Long idUsuario;

    @NotNull(message = "la id_pelicula, no puede ser nula")
    private Long idPelicula;
}