package cl.duoc.mi_lista_service.mapper;

import cl.duoc.mi_lista_service.client.PeliculaFeignClient;
import cl.duoc.mi_lista_service.dto.ItemListaDTO;
import cl.duoc.mi_lista_service.dto.PeliculaDTO;
import cl.duoc.mi_lista_service.model.ItemLista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemListaMapper {

    @Autowired
    private PeliculaFeignClient peliculaClient;

    public ItemListaDTO toDTO(ItemLista item) {
        if (item == null) return null;

        ItemListaDTO dto = new ItemListaDTO();
        dto.setIdUsuario(item.getIdUsuario());
        dto.setIdPelicula(item.getIdPelicula());
        dto.setFechaAgregado(item.getFechaAgregado());

        try {
            PeliculaDTO pelicula = peliculaClient.obtenerPorId(item.getIdPelicula());
            if (pelicula != null) {
                dto.setNombrePelicula(pelicula.getNombre());
            }
        } catch (Exception e) {
            dto.setNombrePelicula("Película no disponible");
        }

        return dto;
    }
}