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
                .map(d -> obtenerEstadisticasDirector(d.getId()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DirectorDTO findById(Long id) {
        return obtenerEstadisticasDirector(id);
    }

    @Transactional
    public DirectorDTO save(Director director) {
        return mapper.toDTO(directorRepository.save(director));
    }

    @Transactional
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }

    @Transactional
    public DirectorDTO update(Long id, Director directorDetails) {
        return directorRepository.findById(id).map(existente -> {
            existente.setNombre(directorDetails.getNombre());
            existente.setApellido(directorDetails.getApellido());
            existente.setNacionalidad(directorDetails.getNacionalidad());
            existente.setFechaNacimiento(directorDetails.getFechaNacimiento());
            return mapper.toDTO(directorRepository.save(existente));
        }).orElse(null);
    }

    public DirectorDTO obtenerEstadisticasDirector(Long idDirector) {
        Director local = directorRepository.findById(idDirector).orElse(null);
        if (local == null) return null;
        DirectorDTO dto = mapper.toDTO(local);
        try {
            List<Long> ids = peliculaClient.obtenerIdsPeliculasPorDirector(idDirector);
            if (ids != null && !ids.isEmpty()) {
                dto.setCantidadPeliculas(ids.size());
                Double promedio = calificacionClient.obtenerPromedioPorListaDePeliculas(ids);
                dto.setPromedioEstrellas(promedio != null ? promedio : 0.0);
            }
        } catch (Exception e) {
            System.err.println("Error con servicios externos: " + e.getMessage());
        }
        return dto;
    }

    // REPORTES
    public List<DirectorDTO> findByNacionalidad(String nacionalidad) {
        return directorRepository.findByNacionalidadIgnoreCase(nacionalidad)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<DirectorDTO> findByApellido(String apellido) {
        return directorRepository.findByApellidoContainingIgnoreCase(apellido)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<DirectorDTO> findByNombreYApellido(String nombre, String apellido) {
        return directorRepository.findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(nombre, apellido)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}