package cl.duoc.director_service.mapper;

import cl.duoc.director_service.dto.DirectorDTO;
import cl.duoc.director_service.model.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {

    public DirectorDTO toDTO(Director director) {
        if (director == null) return null;

        DirectorDTO dto = new DirectorDTO();
        dto.setId(director.getId());
        dto.setNombre(director.getNombre());
        dto.setApellido(director.getApellido());
        dto.setFechaNacimiento(director.getFechaNacimiento());
        dto.setNacionalidad(director.getNacionalidad());
        dto.setCantidadPeliculas(0);
        dto.setPromedioEstrellas(0.0);

        return dto;
    }
}
