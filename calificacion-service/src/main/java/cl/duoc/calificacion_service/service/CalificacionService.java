package cl.duoc.calificacion_service.service;

import cl.duoc.calificacion_service.model.Calificacion;
import cl.duoc.calificacion_service.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    // --- EL METODO MATEMATICO (Para el Feign Client del Director) ---
    @Transactional(readOnly = true)
    public Double obtenerPromedioMasivo(List<Long> peliculasIds) {
        if (peliculasIds == null || peliculasIds.isEmpty()) {
            return 0.0;
        }
        Double promedio = calificacionRepository.calcularPromedioPorListaPeliculas(peliculasIds);
        return promedio != null ? promedio : 0.0;
    }

    // --- OPERACIONES CRUD ESTANDAR ---

    @Transactional(readOnly = true)
    public List<Calificacion> findAll() {
        return calificacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Calificacion findById(Long id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    @Transactional
    public Calificacion save(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Transactional
    public void delete(Long id) {
        calificacionRepository.deleteById(id);
    }

    @Transactional
    public Calificacion update(Long id, Calificacion calificacionActualizada) {
        Calificacion calificacionExistente = calificacionRepository.findById(id).orElse(null);
        if (calificacionExistente == null) return null;

        // Actualizamos los campos
        calificacionExistente.setPuntos(calificacionActualizada.getPuntos());
        calificacionExistente.setFechaCalificacion(calificacionActualizada.getFechaCalificacion());
        calificacionExistente.setIdUsuario(calificacionActualizada.getIdUsuario());
        calificacionExistente.setIdPelicula(calificacionActualizada.getIdPelicula());

        return calificacionRepository.save(calificacionExistente);
    }
}
