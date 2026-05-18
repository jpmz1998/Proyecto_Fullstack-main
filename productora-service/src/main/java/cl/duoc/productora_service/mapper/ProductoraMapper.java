package cl.duoc.productora_service.mapper;

import cl.duoc.productora_service.dto.ProductoraDTO;
import cl.duoc.productora_service.model.Productora;
import org.springframework.stereotype.Component;

@Component
public class ProductoraMapper {

    public ProductoraDTO toDTO(Productora productora) {
        if (productora == null) return null;

        ProductoraDTO dto = new ProductoraDTO();
        dto.setId(productora.getId());
        dto.setNombre(productora.getNombre());
        dto.setPaisUbicacion(productora.getPaisUbicacion());
        dto.setAnioFundacion(productora.getAnioFundacion());
        return dto;
    }
}
