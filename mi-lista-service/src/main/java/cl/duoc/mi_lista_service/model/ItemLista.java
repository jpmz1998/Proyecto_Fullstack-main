package cl.duoc.mi_lista_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemLista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Enlaces lógicos
    @NotNull(message = "no puede ser nulo el idUsuario")
    private Long idUsuario;

    @NotNull(message = "no puede ser nulo el idPelicula")
    private Long idPelicula;

    // Para saber cuándo la guardó
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "la fecha no pueder ser nula")
    private LocalDate fechaAgregado;
}
