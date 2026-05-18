package cl.duoc.mi_lista_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ItemListaDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El ID de la película es obligatorio")
    private Long idPelicula;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaAgregado;

    // Campo enriquecido vía Feign
    @NotBlank( message = "el nombre de la pelicula nop puede estar vacio")
    @Size(min = 3,max = 25,message = "El nombre de la pelicula tiene que estar entre 3 a 25 caracteres")
    private String nombrePelicula;
}
