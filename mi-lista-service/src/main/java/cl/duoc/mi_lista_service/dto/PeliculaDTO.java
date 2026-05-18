package cl.duoc.mi_lista_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PeliculaDTO {

    @NotNull(message = "no puede estar nuiulo ")
    private Long id;


    @NotBlank( message = "el nombre de la pelicula nop puede estar vacio")
    @Size(min = 3,max = 25,message = "El nombre de la pelicula tiene que estar entre 3 a 25 caracteres")
    private String nombre;
}
