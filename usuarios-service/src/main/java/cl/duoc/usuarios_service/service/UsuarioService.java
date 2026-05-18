package cl.duoc.usuarios_service.service;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.mapper.UsuarioMapper;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper mapper;

    // 1. Listar todos (retorna DTOs sin password)
    public List<UsuarioDTO> findAll() {
        return mapper.toListDTO(usuarioRepository.findAll());
    }

    // 2. Buscar por ID (retorna DTO seguro)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return mapper.toDTO(usuario);
    }

    // 3. Crear nuevo usuario
    public UsuarioDTO save(Usuario usuario) {
        Usuario guardado = usuarioRepository.save(usuario);
        return mapper.toDTO(guardado);
    }

    // 4. Eliminar por ID
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    // 5. Actualizar usuario
    public UsuarioDTO update(Long id, Usuario usuario) {
        Usuario usuarioActualizado = usuarioRepository.findById(id).orElse(null);
        if (usuarioActualizado == null) return null;

        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setRol(usuario.getRol());

        return mapper.toDTO(usuarioRepository.save(usuarioActualizado));
    }
}
