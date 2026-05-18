package cl.duoc.pelicula_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PeliculaDTO {

    private Long id;

    private String nombre;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate estreno;

    private String genero;

    private String descripcion;

    private Long directorId;

    private Long productoraId;

    private Integer duracionMinutos;

    private Boolean esPara18;

    // Campos enriquecidos desde otros microservicios
    private String nombreDirector;

    private String nombreProductora;
}
