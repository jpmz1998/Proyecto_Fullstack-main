package cl.duoc.usuarios_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede ser nulo ni estar en blanco")
    @Size(min = 3, message = "El nombre debe tener un mínimo de 3 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un formato de correo válido")
    private String email;
}
