package cl.duoc.usuarios_service.mapper;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    public List<UsuarioDTO> toListDTO(List<Usuario> listado) {
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
