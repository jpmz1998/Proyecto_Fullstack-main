package cl.duoc.suscripciones_service.mapper;

import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import org.springframework.stereotype.Component;

@Component
public class SuscripcionMapper {

    public SuscripcionDTO toDTO(Suscripcion suscripcion, UsuarioDTO usuarioDTO) {
        if (suscripcion == null) return null;

        SuscripcionDTO dto = new SuscripcionDTO();
        dto.setId(suscripcion.getId());
        dto.setTipoPlan(suscripcion.getTipoPlan());
        dto.setMonto(suscripcion.getMonto());
        dto.setFechaInicio(suscripcion.getFechaInicio());
        dto.setEstado(suscripcion.getEstado());
        dto.setUsuario(usuarioDTO);

        return dto;
    }
}
