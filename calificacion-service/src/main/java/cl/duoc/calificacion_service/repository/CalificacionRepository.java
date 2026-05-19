package cl.duoc.calificacion_service.repository;

import cl.duoc.calificacion_service.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    // Para feign client de director
    @Query("SELECT AVG(c.puntos) FROM Calificacion c WHERE c.idPelicula IN :peliculasIds")
    Double calcularPromedioPorListaPeliculas(@Param("peliculasIds") List<Long> peliculasIds);

    // Reporte 1: Todas las calificaciones de una película
    List<Calificacion> findByIdPelicula(Long idPelicula);

    // Reporte 2: Todas las calificaciones de un usuario
    List<Calificacion> findByIdUsuario(Long idUsuario);

    // Reporte 3: Promedio de una película específica
    @Query("SELECT AVG(c.puntos) FROM Calificacion c WHERE c.idPelicula = :idPelicula")
    Double calcularPromedioPorPelicula(@Param("idPelicula") Long idPelicula);

    // Reporte 4: Filtrar calificaciones por puntaje mínimo
    List<Calificacion> findByPuntosGreaterThanEqual(Integer puntosMin);
}
