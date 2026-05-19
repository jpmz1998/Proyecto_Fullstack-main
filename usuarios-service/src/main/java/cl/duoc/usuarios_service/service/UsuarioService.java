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

    public List<UsuarioDTO> findAll() {
        return mapper.toListDTO(usuarioRepository.findAll());
    }

    public UsuarioDTO findById(Long id) {
        return mapper.toDTO(usuarioRepository.findById(id).orElse(null));
    }

    public UsuarioDTO save(Usuario usuario) {
        return mapper.toDTO(usuarioRepository.save(usuario));
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO update(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setNombre(usuario.getNombre());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        existente.setRol(usuario.getRol());
        return mapper.toDTO(usuarioRepository.save(existente));
    }

    // REPORTES
    public UsuarioDTO findByEmail(String email) {
        return mapper.toDTO(usuarioRepository.findByEmail(email).orElse(null));
    }

    public List<UsuarioDTO> findByRol(String rol) {
        return mapper.toListDTO(usuarioRepository.findByRol(rol));
    }

    public List<UsuarioDTO> findByNombre(String nombre) {
        return mapper.toListDTO(usuarioRepository.findByNombreContainingIgnoreCase(nombre));
    }
}
