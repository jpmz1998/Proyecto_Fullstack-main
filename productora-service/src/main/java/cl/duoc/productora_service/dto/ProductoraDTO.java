package cl.duoc.productora_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoraDTO {

    private Long id;

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Size(min = 3, max = 50, message = "El país debe tener entre 3 y 50 caracteres")
    private String paisUbicacion;

    @Min(value = 1800, message = "El año de fundación no puede ser anterior a 1800")
    @Max(value = 2026, message = "El año de fundación no puede ser posterior al año actual")
    private Integer anioFundacion;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidadPeliculasFinanciadas;
}
