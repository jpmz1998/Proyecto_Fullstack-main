package cl.duoc.calificacion_service.service;

import cl.duoc.calificacion_service.dto.CalificacionDTO;
import cl.duoc.calificacion_service.mapper.CalificacionMapper;
import cl.duoc.calificacion_service.model.Calificacion;
import cl.duoc.calificacion_service.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private CalificacionMapper mapper;

    public List<CalificacionDTO> findAll() {
        return calificacionRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();}

    public CalificacionDTO findById(Long id) {
        Calificacion c = calificacionRepository.findById(id).orElse(null);
        return mapper.toDTO(c);

    }
    public CalificacionDTO save(Calificacion c) {
        return mapper.toDTO(calificacionRepository.save(c)); }


    public void delete(Long id) { calificacionRepository.deleteById(id); }

    public CalificacionDTO update(Long id, Calificacion actualizada) {
        Calificacion existente = calificacionRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setPuntos(actualizada.getPuntos());
        existente.setFechaCalificacion(actualizada.getFechaCalificacion());
        existente.setIdUsuario(actualizada.getIdUsuario());
        existente.setIdPelicula(actualizada.getIdPelicula());
        return mapper.toDTO(calificacionRepository.save(existente));
    }

    public Double obtenerPromedioMasivo(List<Long> ids) {
        return calificacionRepository.calcularPromedioPorListaPeliculas(ids);
    }

    // REPORTES
    public List<CalificacionDTO> findByPelicula(Long idPelicula) {
        return calificacionRepository.findByIdPelicula(idPelicula)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CalificacionDTO> findByUsuario(Long idUsuario) {
        return calificacionRepository.findByIdUsuario(idUsuario).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Double promedioPorPelicula(Long idPelicula) {
        return calificacionRepository.calcularPromedioPorPelicula(idPelicula);
    }

    public List<CalificacionDTO> findByPuntajeMinimo(Integer puntaje) {
        return calificacionRepository.findByPuntosGreaterThanEqual(puntaje)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}