package cl.duoc.suscripciones_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO { //ver si se deben poner filtos de notnull, notblank, etc

    @NotNull(message = "la id no puede estar nula")
    private Long id;

    @NotBlank(message = "el nombre no puede estar en vacio")
    @Size(min = 2,max = 25,message = "el campo tiene que estar entre 2 y 25 caracteres")
    private String nombre;

    @NotBlank(message = "el nombre no puede estar en vacio")
    @Size(min = 2,max = 25,message = "el campo tiene que estar entre 2 y 25 caracteres")
    private String email;
}