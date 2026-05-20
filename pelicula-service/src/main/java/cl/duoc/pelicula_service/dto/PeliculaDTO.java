package cl.duoc.pelicula_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PeliculaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La fecha de estreno no puede ser nula")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate estreno;

    @NotBlank(message = "El género no puede estar vacío")
    @Size(min = 1, max = 50, message = "El género debe tener entre 1 y 50 caracteres")
    private String genero;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El id del director no puede ser nulo")
    private Long directorId;

    @NotNull(message = "El id de la productora no puede ser nulo")
    private Long productoraId;

    @NotNull(message = "La duración no puede ser nula")
    @Min(value = 1, message = "La duración debe ser al menos 1 minuto")
    @Max(value = 600, message = "La duración no puede superar los 600 minutos")
    private Integer duracionMinutos;

    @NotNull(message = "Debe indicar si la película es para mayores de 18")
    private Boolean esPara18;

    // Campos enriquecidos desde otros microservicios (sin validaciones)
    private String nombreDirector;

    private String nombreProductora;
}
