package cl.duoc.calificacion_service.service;

import cl.duoc.calificacion_service.model.Calificacion;
import cl.duoc.calificacion_service.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Calificacion> findAll() { return calificacionRepository.findAll(); }
    public Calificacion findById(Long id) { return calificacionRepository.findById(id).orElse(null); }
    public Calificacion save(Calificacion c) { return calificacionRepository.save(c); }
    public void delete(Long id) { calificacionRepository.deleteById(id); }

    public Calificacion update(Long id, Calificacion actualizada) {
        Calificacion existente = calificacionRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setPuntos(actualizada.getPuntos());
        existente.setFechaCalificacion(actualizada.getFechaCalificacion());
        existente.setIdUsuario(actualizada.getIdUsuario());
        existente.setIdPelicula(actualizada.getIdPelicula());
        return calificacionRepository.save(existente);
    }

    public Double obtenerPromedioMasivo(List<Long> ids) {
        return calificacionRepository.calcularPromedioPorListaPeliculas(ids);
    }

    // REPORTES
    public List<Calificacion> findByPelicula(Long idPelicula) {
        return calificacionRepository.findByIdPelicula(idPelicula);
    }

    public List<Calificacion> findByUsuario(Long idUsuario) {
        return calificacionRepository.findByIdUsuario(idUsuario);
    }

    public Double promedioPorPelicula(Long idPelicula) {
        return calificacionRepository.calcularPromedioPorPelicula(idPelicula);
    }

    public List<Calificacion> findByPuntajeMinimo(Integer puntaje) {
        return calificacionRepository.findByPuntosGreaterThanEqual(puntaje);
    }
}