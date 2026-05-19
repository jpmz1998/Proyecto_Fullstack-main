package cl.duoc.pelicula_service.service;

import cl.duoc.pelicula_service.client.DirectorFeignClient;
import cl.duoc.pelicula_service.client.ProductoraFeignClient;
import cl.duoc.pelicula_service.dto.PeliculaDTO;
import cl.duoc.pelicula_service.mapper.PeliculaMapper;
import cl.duoc.pelicula_service.model.Pelicula;
import cl.duoc.pelicula_service.repository.PeliculaRepository;
import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaMapper mapper;

    @Autowired
    private DirectorFeignClient directorFeignClient;

    @Autowired
    private ProductoraFeignClient productoraFeignClient;

    public List<PeliculaDTO> findAll() { return peliculaRepository.findAll().stream().map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public PeliculaDTO findById(Long id) {
        return mapper.toDTO(peliculaRepository.findById(id).orElse(null));
    }

    public PeliculaDTO save(Pelicula pelicula) {
        return mapper.toDTO(peliculaRepository.save(pelicula));
    }

    public void delete(Long id) { peliculaRepository.deleteById(id); }

    public PeliculaDTO update(Long id, Pelicula actualizada) {
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
        return mapper.toDTO(peliculaRepository.save(existente));
    }

    public List<Long> obtenerIdsPorDirector(Long directorId) {
        return peliculaRepository.findIdsByDirectorId(directorId);
    }

    public List<Long> obtenerIdsPorProductora(Long productoraId) {
        return peliculaRepository.findIdsByProductoraId(productoraId);
    }

    // REPORTES
    public List<PeliculaDTO> filtrarPorClasificacionEdad(Boolean esPara18) {
        return peliculaRepository.findByEsPara18(esPara18)
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PeliculaDTO> findByGenero(String genero) {
        return peliculaRepository.findByGeneroIgnoreCase(genero)
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PeliculaDTO> findByDuracion(Integer min, Integer max) {
        return peliculaRepository.findByDuracionMinutosBetween(min, max).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PeliculaDTO> findByNombre(String nombre) {
        return peliculaRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}