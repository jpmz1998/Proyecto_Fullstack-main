package cl.duoc.recomendacion_service.mapper;

import cl.duoc.recomendacion_service.client.PeliculaFeignClient;
import cl.duoc.recomendacion_service.dto.PeliculaDTO;
import cl.duoc.recomendacion_service.dto.RecomendacionDTO;
import cl.duoc.recomendacion_service.model.Recomendacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecomendacionMapper {

    @Autowired
    private PeliculaFeignClient peliculaClient;

    public RecomendacionDTO toDTO(Recomendacion recomendacion) {
        if (recomendacion == null) return null;

        RecomendacionDTO dto = new RecomendacionDTO();
        dto.setId(recomendacion.getId());
        dto.setIdUsuario(recomendacion.getIdUsuario());
        dto.setIdPelicula(recomendacion.getIdPelicula());
        dto.setMensajePersonalizado(recomendacion.getMensajePersonalizado());
        dto.setNivelConfianza(recomendacion.getNivelConfianza());
        dto.setFechaRecomendacion(recomendacion.getFechaRecomendacion());

        // Intentamos enriquecer el DTO llamando al otro microservicio
        try {
            PeliculaDTO pelicula = peliculaClient.obtenerPorId(recomendacion.getIdPelicula());
            if (pelicula != null) {
                dto.setNombrePelicula(pelicula.getNombre());
            }
        } catch (Exception e) {
            dto.setNombrePelicula("Información de película no disponible");
        }

        return dto;
    }
}
