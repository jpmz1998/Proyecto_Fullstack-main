package cl.duoc.director_service.service;

import cl.duoc.director_service.client.CalificacionFeignClient;
import cl.duoc.director_service.client.PeliculaFeignClient;
import cl.duoc.director_service.dto.DirectorDTO;
import cl.duoc.director_service.mapper.DirectorMapper;
import cl.duoc.director_service.model.Director;
import cl.duoc.director_service.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private DirectorMapper mapper;

    @Autowired
    private PeliculaFeignClient peliculaClient;

    @Autowired
    private CalificacionFeignClient calificacionClient;

    @Transactional(readOnly = true)
    public List<DirectorDTO> findAll() {
        return directorRepository.findAll()
                .stream()
                .map(director -> obtenerEstadisticasDirector(director.getId()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DirectorDTO findById(Long id) {
        return obtenerEstadisticasDirector(id);
    }

    @Transactional
    public DirectorDTO save(Director director) {
        Director directorGuardado = directorRepository.save(director);
        return mapper.toDTO(directorGuardado);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el director con ID: " + id);
        }
        directorRepository.deleteById(id);
    }

    @Transactional
    public DirectorDTO update(Long id, Director directorDetails) {
        return directorRepository.findById(id).map(directorExistente -> {
            directorExistente.setNombre(directorDetails.getNombre());
            directorExistente.setApellido(directorDetails.getApellido());
            directorExistente.setNacionalidad(directorDetails.getNacionalidad());
            directorExistente.setFechaNacimiento(directorDetails.getFechaNacimiento());
            Director actualizado = directorRepository.save(directorExistente);
            return mapper.toDTO(actualizado);
        }).orElse(null);
    }

    public DirectorDTO obtenerEstadisticasDirector(Long idDirector) {
        Director directorLocal = directorRepository.findById(idDirector).orElse(null);
        if (directorLocal == null) return null;

        DirectorDTO dto = mapper.toDTO(directorLocal);

        try {
            List<Long> idsPeliculas = peliculaClient.obtenerIdsPeliculasPorDirector(idDirector);

            if (idsPeliculas != null && !idsPeliculas.isEmpty()) {
                dto.setCantidadPeliculas(idsPeliculas.size());
                Double promedio = calificacionClient.obtenerPromedioPorListaDePeliculas(idsPeliculas);
                dto.setPromedioEstrellas(promedio != null ? promedio : 0.0);
            }
        } catch (Exception e) {
            System.err.println("Error de comunicación con servicios externos: " + e.getMessage());
        }

        return dto;
    }
}
