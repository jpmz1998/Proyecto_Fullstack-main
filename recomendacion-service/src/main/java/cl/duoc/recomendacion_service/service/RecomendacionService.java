package cl.duoc.recomendacion_service.service;

import cl.duoc.recomendacion_service.dto.RecomendacionDTO;
import cl.duoc.recomendacion_service.mapper.RecomendacionMapper;
import cl.duoc.recomendacion_service.model.Recomendacion;
import cl.duoc.recomendacion_service.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @Autowired
    private RecomendacionMapper mapper;

    public List<RecomendacionDTO> findAll() {
        return recomendacionRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public RecomendacionDTO findById(Long id) {
         Recomendacion recomendacion = recomendacionRepository.findById(id).orElse(null);
         return mapper.toDTO(recomendacion);
    }

    public RecomendacionDTO save(Recomendacion r) {
        Recomendacion rSave = recomendacionRepository.save(r);
        return mapper.toDTO(rSave);
    }

    public void delete(Long id) {
        recomendacionRepository.deleteById(id);
    }

    public RecomendacionDTO update(Long id, Recomendacion actualizada) {
        Recomendacion existente = recomendacionRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setIdUsuario(actualizada.getIdUsuario());
        existente.setIdPelicula(actualizada.getIdPelicula());
        existente.setMensajePersonalizado(actualizada.getMensajePersonalizado());
        existente.setNivelConfianza(actualizada.getNivelConfianza());
        existente.setFechaRecomendacion(actualizada.getFechaRecomendacion());
        return mapper.toDTO(recomendacionRepository.save(existente));
    }

    // REPORTES
    public List<RecomendacionDTO> findByUsuario(Long idUsuario) {
        return recomendacionRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecomendacionDTO> findByNivelConfianza(Integer nivelMinimo) {
        return recomendacionRepository.findByNivelConfianzaGreaterThanEqual(nivelMinimo)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecomendacionDTO> findByPelicula(Long idPelicula) {
        return recomendacionRepository.findByIdPelicula(idPelicula)
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}