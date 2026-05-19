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

    public SuscripcionDTO save(Suscripcion suscripcion) {
        Suscripcion guardada = suscripcionRepository.save(suscripcion);
        UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(guardada.getUsuarioId());
        return mapper.toDTO(guardada, user);
    }

    public SuscripcionDTO findById(Long id) {
        Suscripcion s = suscripcionRepository.findById(id).orElse(null);
        if (s == null) return null;
        UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(s.getUsuarioId());
        return mapper.toDTO(s, user);
    }

    public List<SuscripcionDTO> findAll() {
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion s : suscripcionRepository.findAll()) {
            UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(s.getUsuarioId());
            dtos.add(mapper.toDTO(s, user));
        }
        return dtos;
    }

    public SuscripcionDTO update(Long id, Suscripcion actualizada) {
        Suscripcion existente = suscripcionRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setTipoPlan(actualizada.getTipoPlan());
        existente.setMonto(actualizada.getMonto());
        existente.setFechaInicio(actualizada.getFechaInicio());
        existente.setEstado(actualizada.getEstado());
        existente.setUsuarioId(actualizada.getUsuarioId());
        Suscripcion guardada = suscripcionRepository.save(existente);
        UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(guardada.getUsuarioId());
        return mapper.toDTO(guardada, user);
    }

    public boolean delete(Long id) {
        if (!suscripcionRepository.existsById(id)) return false;
        suscripcionRepository.deleteById(id);
        return true;
    }

    // REPORTES
    public List<SuscripcionDTO> findByEstado(String estado) {
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion s : suscripcionRepository.findByEstado(estado)) {
            UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(s.getUsuarioId());
            dtos.add(mapper.toDTO(s, user));
        }
        return dtos;
    }

    public List<SuscripcionDTO> findByPlan(String tipoPlan) {
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion s : suscripcionRepository.findByTipoPlan(tipoPlan)) {
            UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(s.getUsuarioId());
            dtos.add(mapper.toDTO(s, user));
        }
        return dtos;
    }

    public List<SuscripcionDTO> findByUsuario(Long idUsuario) {
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion s : suscripcionRepository.findByUsuarioId(idUsuario)) {
            UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(s.getUsuarioId());
            dtos.add(mapper.toDTO(s, user));
        }
        return dtos;
    }

    public List<SuscripcionDTO> findByRangoDeMonto(Integer montoMin, Integer montoMax) {
        List<SuscripcionDTO> dtos = new ArrayList<>();
        for (Suscripcion s : suscripcionRepository.findByRangoDeMonto(montoMin, montoMax)) {
            UsuarioDTO user = usuarioClient.obtenerUsuarioPorId(s.getUsuarioId());
            dtos.add(mapper.toDTO(s, user));
        }
        return dtos;
    }
}