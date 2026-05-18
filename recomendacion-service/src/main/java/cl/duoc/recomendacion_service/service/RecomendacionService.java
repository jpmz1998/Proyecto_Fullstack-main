package cl.duoc.recomendacion_service.service;

import cl.duoc.recomendacion_service.model.Recomendacion;
import cl.duoc.recomendacion_service.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    // --- LECTURA ---
    @Transactional(readOnly = true)
    public List<Recomendacion> findAll() {
        return recomendacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Recomendacion findById(Long id) {
        return recomendacionRepository.findById(id).orElse(null);
    }

    // Metodo custom para buscar por usuario
    @Transactional(readOnly = true)
    public List<Recomendacion> obtenerPorUsuario(Long idUsuario) {
        return recomendacionRepository.findByIdUsuario(idUsuario);
    }

    // --- ESCRITURA (CRUD) ---
    @Transactional
    public Recomendacion save(Recomendacion recomendacion) {
        // Asignamos la fecha del sistema automáticamente
        recomendacion.setFechaRecomendacion(LocalDate.now());
        return recomendacionRepository.save(recomendacion);
    }

    @Transactional
    public Recomendacion update(Long id, Recomendacion recomendacionActualizada) {
        Recomendacion recomendacionExistente = recomendacionRepository.findById(id).orElse(null);
        if (recomendacionExistente == null) return null;

        recomendacionExistente.setIdUsuario(recomendacionActualizada.getIdUsuario());
        recomendacionExistente.setIdPelicula(recomendacionActualizada.getIdPelicula());
        recomendacionExistente.setMensajePersonalizado(recomendacionActualizada.getMensajePersonalizado());
        recomendacionExistente.setNivelConfianza(recomendacionActualizada.getNivelConfianza());
        // Nota: Por regla general, no actualizamos la fecha original en la que se hizo la recomendación

        return recomendacionRepository.save(recomendacionExistente);
    }

    @Transactional
    public void delete(Long id) {
        recomendacionRepository.deleteById(id);
    }
}
