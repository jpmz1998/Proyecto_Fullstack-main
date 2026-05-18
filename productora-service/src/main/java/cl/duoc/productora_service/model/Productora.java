package cl.duoc.productora_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Productora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la productora no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El país de ubicación es obligatorio")
    @Size(min = 3, max = 50, message = "El país debe tener entre 3 y 50 caracteres")
    private String paisUbicacion;

    @NotNull(message = "El año de fundación es obligatorio")
    @Min(value = 1800, message = "El año de fundación no puede ser anterior a 1800")
    @Max(value = 2026, message = "El año de fundación no puede ser posterior al año actual")
    private Integer anioFundacion;
}
