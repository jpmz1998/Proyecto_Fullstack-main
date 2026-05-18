package cl.duoc.director_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Director {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotBlank( message = "el nombnre no puede estar null")
     @Size(min = 2,max = 25,message = "entre 0 a 25 caracteres")
     private String nombre;

     @NotBlank(message = "el apellido no puede estar vacio")
     @Size(min = 0,max = 25,message = "entre 0 a 25 caracteres")
     private String apellido;

     @NotNull(message = "la fecha es obligatoria")
     @DateTimeFormat(pattern = "dd-MM-yyyy")
     @JsonFormat(pattern = "dd-MM-yyyy")
     private LocalDate fechaNacimiento;

    @NotBlank( message = "La nacionalidad no puede estar vacia")
    @Size(min = 3,max = 25,message = "entre 0 a 25 caracteres")
     private String nacionalidad;
}
