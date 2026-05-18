package cl.duoc.calificacion_service.mapper;

import cl.duoc.calificacion_service.dto.CalificacionDTO;
import cl.duoc.calificacion_service.model.Calificacion;
import org.springframework.stereotype.Component;

@Component
public class CalificacionMapper {

    public CalificacionDTO toDTO(Calificacion calificacion) {
        if (calificacion == null) return null;

        CalificacionDTO dto = new CalificacionDTO();
        dto.setFechaCalificacion(calificacion.getFechaCalificacion());
        dto.setPuntos(calificacion.getPuntos());
        dto.setFechaCalificacion(calificacion.getFechaCalificacion());
        dto.setIdUsuario(calificacion.getIdUsuario());
        dto.setIdPelicula(calificacion.getIdPelicula());

        return dto;
    }
}
