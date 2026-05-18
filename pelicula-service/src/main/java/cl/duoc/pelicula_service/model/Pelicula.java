package cl.duoc.pelicula_service.model;

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
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la película no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull(message = "La fecha de estreno es obligatoria")
    @PastOrPresent(message = "La fecha de estreno no puede ser futura")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate estreno;

    @NotBlank(message = "El género es obligatorio")
    @Size(min = 3, max = 50, message = "El género debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
            message = "El género solo puede contener letras y espacios")
    @Column(nullable = false, length = 50)
    private String genero;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Column(length = 500)
    private String descripcion;

    // Enlaces logicos (Foreign Keys virtuales hacia otros microservicios)
    @NotNull(message = "El ID del director es obligatorio")
    @Positive(message = "El ID del director debe ser un número positivo")
    private Long directorId;  // FK hacia director-service

    @NotNull(message = "El ID de la productora es obligatorio")
    @Positive(message = "El ID de la productora debe ser un número positivo")
    private Long productoraId;  // FK hacia productora-service

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración mínima es 1 minuto")
    @Max(value = 480, message = "La duración no puede exceder 480 minutos (8 horas)")
    private Integer duracionMinutos;

    @NotNull(message = "El campo 'esPara18' es obligatorio")
    private Boolean esPara18;
}
