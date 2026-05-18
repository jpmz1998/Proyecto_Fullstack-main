package cl.duoc.pelicula_service.service;

import cl.duoc.pelicula_service.model.Pelicula;
import cl.duoc.pelicula_service.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Transactional(readOnly = true)
    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pelicula findById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Long> obtenerIdsPorDirector(Long idDirector) {
        return peliculaRepository.findIdsByDirectorId(idDirector);
    }

    @Transactional
    public Pelicula save(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Transactional
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Transactional
    public Pelicula update(Long id, Pelicula peliculaActualizada) {
        Pelicula peliculaExistente = peliculaRepository.findById(id).orElse(null);
        if (peliculaExistente == null) return null;

        peliculaExistente.setNombre(peliculaActualizada.getNombre());
        peliculaExistente.setEstreno(peliculaActualizada.getEstreno());
        peliculaExistente.setGenero(peliculaActualizada.getGenero());
        peliculaExistente.setDescripcion(peliculaActualizada.getDescripcion());
        peliculaExistente.setDuracionMinutos(peliculaActualizada.getDuracionMinutos());
        peliculaExistente.setEsPara18(peliculaActualizada.getEsPara18());

        // Mantenemos los enlaces logicos
        peliculaExistente.setDirectorId(peliculaActualizada.getDirectorId());
        peliculaExistente.setProductoraId(peliculaActualizada.getProductoraId());

        return peliculaRepository.save(peliculaExistente);
    }

    // --- ¡NUEVO METODO! ---
    @Transactional(readOnly = true)
    public List<Long> obtenerIdsPorProductora(Long idProductora) {
        return peliculaRepository.findIdsByProductoraId(idProductora);
    }

    // --- ¡NUEVO METODO DE LOGICA: FILTRO DE EDAD! ---
    @Transactional(readOnly = true)
    public List<Pelicula> filtrarPorClasificacionEdad(Boolean esPara18) {
        return peliculaRepository.findByEsPara18(esPara18);
    }
}