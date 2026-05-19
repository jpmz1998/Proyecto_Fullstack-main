package cl.duoc.recomendacion_service.service;

import cl.duoc.recomendacion_service.model.Recomendacion;
import cl.duoc.recomendacion_service.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    public List<Recomendacion> findAll() { return recomendacionRepository.findAll(); }
    public Recomendacion findById(Long id) { return recomendacionRepository.findById(id).orElse(null); }
    public Recomendacion save(Recomendacion r) { return recomendacionRepository.save(r); }
    public void delete(Long id) { recomendacionRepository.deleteById(id); }

    public Recomendacion update(Long id, Recomendacion actualizada) {
        Recomendacion existente = recomendacionRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setIdUsuario(actualizada.getIdUsuario());
        existente.setIdPelicula(actualizada.getIdPelicula());
        existente.setMensajePersonalizado(actualizada.getMensajePersonalizado());
        existente.setNivelConfianza(actualizada.getNivelConfianza());
        existente.setFechaRecomendacion(actualizada.getFechaRecomendacion());
        return recomendacionRepository.save(existente);
    }

    // REPORTES
    public List<Recomendacion> findByUsuario(Long idUsuario) {
        return recomendacionRepository.findByIdUsuario(idUsuario);
    }

    public List<Recomendacion> findByNivelConfianza(Integer nivelMinimo) {
        return recomendacionRepository.findByNivelConfianzaGreaterThanEqual(nivelMinimo);
    }

    public List<Recomendacion> findByPelicula(Long idPelicula) {
        return recomendacionRepository.findByIdPelicula(idPelicula);
    }
}