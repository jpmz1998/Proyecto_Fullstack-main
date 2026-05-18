package cl.duoc.recomendacion_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PeliculaDTO {

    @NotNull(message = "esta id nop puede ser nula")
    private Long id;

    @NotBlank(message = "El nombre de la película no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;
}
