package cl.duoc.pelicula_service.mapper;

import cl.duoc.pelicula_service.client.DirectorFeignClient;
import cl.duoc.pelicula_service.client.ProductoraFeignClient;
import cl.duoc.pelicula_service.dto.DirectorDTO;
import cl.duoc.pelicula_service.dto.PeliculaDTO;
import cl.duoc.pelicula_service.dto.ProductoraDTO;
import cl.duoc.pelicula_service.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMapper {

    @Autowired
    private DirectorFeignClient directorClient;

    @Autowired
    private ProductoraFeignClient productoraClient;

    public PeliculaDTO toDTO(Pelicula pelicula) {
        if (pelicula == null) return null;

        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(pelicula.getId());
        dto.setNombre(pelicula.getNombre());
        dto.setEstreno(pelicula.getEstreno());
        dto.setGenero(pelicula.getGenero());
        dto.setDescripcion(pelicula.getDescripcion());
        dto.setDuracionMinutos(pelicula.getDuracionMinutos());
        dto.setEsPara18(pelicula.getEsPara18());
        dto.setDirectorId(pelicula.getDirectorId());
        dto.setProductoraId(pelicula.getProductoraId());

        try {
            DirectorDTO director = directorClient.obtenerPorId(pelicula.getDirectorId());
            if (director != null) {
                dto.setNombreDirector(director.getNombre() + " " + director.getApellido());
            }
        } catch (Exception e) {
            dto.setNombreDirector("Director no disponible");
        }

        try {
            ProductoraDTO productora = productoraClient.obtenerPorId(pelicula.getProductoraId());
            if (productora != null) {
                dto.setNombreProductora(productora.getNombre());
            }
        } catch (Exception e) {
            dto.setNombreProductora("Productora no disponible");
        }

        return dto;
    }
}
