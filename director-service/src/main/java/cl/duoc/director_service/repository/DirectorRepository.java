package cl.duoc.director_service.repository;

import cl.duoc.director_service.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    // Reporte 1: Filtrar por nacionalidad
    List<Director> findByNacionalidadIgnoreCase(String nacionalidad);

    // Reporte 2: Buscar por apellido
    List<Director> findByApellidoContainingIgnoreCase(String apellido);

    // Reporte 3: Buscar por nombre y apellido
    List<Director> findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCase(String nombre, String apellido);
}
