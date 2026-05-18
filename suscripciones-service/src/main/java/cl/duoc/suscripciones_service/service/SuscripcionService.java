package cl.duoc.suscripciones_service.service;

import cl.duoc.suscripciones_service.client.UsuarioFeignClient;
import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import cl.duoc.suscripciones_service.mapper.SuscripcionMapper;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private SuscripcionMapper mapper;

    @Autowired
    private UsuarioFeignClient usuarioClient;

    // 1. Crear suscripción
    public SuscripcionDTO save(Suscripcion suscripcion) {
        Suscripcion guardada = suscripcionRepository.save(suscripcion);
        UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(guardada.getUsuarioId());
        return mapper.toDTO(guardada, user);
    }

    // 2. Buscar por ID
    public SuscripcionDTO findById(Long id) {
        Suscripcion suscripcion = suscripcionRepository.findById(id).orElse(null);
        if (suscripcion == null) return null;
        UsuarioDTO usuarioDTO = usuarioClient.obtenerUsuarioPorId(suscripcion.getUsuarioId());
        return mapper.toDTO(suscripcion, usuarioDTO);
    }

    // 3. Listar todas
    public List<SuscripcionDTO> findAll() {
        List<Suscripcion> suscripciones = suscripcionRepository.findAll();
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion sub : suscripciones) {
            UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(sub.getUsuarioId());
            dtos.add(mapper.toDTO(sub, user));
        }
        return dtos;
    }

    // 4. Actualizar suscripción
    public SuscripcionDTO update(Long id, Suscripcion suscripcionActualizada) {
        Suscripcion suscripcionExistente = suscripcionRepository.findById(id).orElse(null);
        if (suscripcionExistente == null) return null;

        suscripcionExistente.setTipoPlan(suscripcionActualizada.getTipoPlan());
        suscripcionExistente.setMonto(suscripcionActualizada.getMonto());
        suscripcionExistente.setFechaInicio(suscripcionActualizada.getFechaInicio());
        suscripcionExistente.setEstado(suscripcionActualizada.getEstado());
        suscripcionExistente.setUsuarioId(suscripcionActualizada.getUsuarioId());

        Suscripcion actualizada = suscripcionRepository.save(suscripcionExistente);
        UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(actualizada.getUsuarioId());
        return mapper.toDTO(actualizada, user);
    }

    // 5. Eliminar suscripción
    public boolean delete(Long id) {
        if (!suscripcionRepository.existsById(id)) return false;
        suscripcionRepository.deleteById(id);
        return true;
    }
}
