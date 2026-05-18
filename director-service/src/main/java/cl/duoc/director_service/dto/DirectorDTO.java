package cl.duoc.director_service.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DirectorDTO {

    private Long id;

    @Size(min = 2, max = 25, message = "el nombre debe tener entre 2 y 25 caracteres")
    private String nombre;

    @Size(min = 2, max = 25, message = "el apellido debe tener entre 2 y 25 caracteres")
    private String apellido;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    @Size(min = 3, max = 25, message = "la nacionalidad debe tener entre 3 y 25 caracteres")
    private String nacionalidad;

    private Integer cantidadPeliculas;
    private Double promedioEstrellas;
}
