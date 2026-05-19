package cl.duoc.pelicula_service.repository;

import cl.duoc.pelicula_service.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // Para feign clients
    @Query("SELECT p.id FROM Pelicula p WHERE p.directorId = :directorId")
    List<Long> findIdsByDirectorId(@Param("directorId") Long directorId);

    @Query("SELECT p.id FROM Pelicula p WHERE p.productoraId = :productoraId")
    List<Long> findIdsByProductoraId(@Param("productoraId") Long productoraId);

    // Reporte 1: Filtrar por clasificación de edad
    List<Pelicula> findByEsPara18(Boolean esPara18);

    // Reporte 2: Filtrar por género
    List<Pelicula> findByGeneroIgnoreCase(String genero);

    // Reporte 3: Filtrar por rango de duración
    List<Pelicula> findByDuracionMinutosBetween(Integer min, Integer max);

    // Reporte 4: Buscar por nombre
    List<Pelicula> findByNombreContainingIgnoreCase(String nombre);
}
