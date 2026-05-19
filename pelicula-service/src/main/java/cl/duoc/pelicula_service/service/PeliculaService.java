package cl.duoc.pelicula_service.service;

import cl.duoc.pelicula_service.model.Pelicula;
import cl.duoc.pelicula_service.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> findAll() { return peliculaRepository.findAll(); }
    public Pelicula findById(Long id) { return peliculaRepository.findById(id).orElse(null); }
    public Pelicula save(Pelicula pelicula) { return peliculaRepository.save(pelicula); }
    public void delete(Long id) { peliculaRepository.deleteById(id); }

    public Pelicula update(Long id, Pelicula actualizada) {
        Pelicula existente = peliculaRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setNombre(actualizada.getNombre());
        existente.setEstreno(actualizada.getEstreno());
        existente.setGenero(actualizada.getGenero());
        existente.setDescripcion(actualizada.getDescripcion());
        existente.setDirectorId(actualizada.getDirectorId());
        existente.setProductoraId(actualizada.getProductoraId());
        existente.setDuracionMinutos(actualizada.getDuracionMinutos());
        existente.setEsPara18(actualizada.getEsPara18());
        return peliculaRepository.save(existente);
    }

    public List<Long> obtenerIdsPorDirector(Long directorId) {
        return peliculaRepository.findIdsByDirectorId(directorId);
    }

    public List<Long> obtenerIdsPorProductora(Long productoraId) {
        return peliculaRepository.findIdsByProductoraId(productoraId);
    }

    // REPORTES
    public List<Pelicula> filtrarPorClasificacionEdad(Boolean esPara18) {
        return peliculaRepository.findByEsPara18(esPara18);
    }

    public List<Pelicula> findByGenero(String genero) {
        return peliculaRepository.findByGeneroIgnoreCase(genero);
    }

    public List<Pelicula> findByDuracion(Integer min, Integer max) {
        return peliculaRepository.findByDuracionMinutosBetween(min, max);
    }

    public List<Pelicula> findByNombre(String nombre) {
        return peliculaRepository.findByNombreContainingIgnoreCase(nombre);
    }
}