package cl.duoc.calificacion_service.repository;

import cl.duoc.calificacion_service.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    // Custom Query: Promedio masivo para una lista de películas
    @Query("SELECT AVG(c.puntos) FROM Calificacion c WHERE c.idPelicula IN :peliculasIds")
    Double calcularPromedioPorListaPeliculas(@Param("peliculasIds") List<Long> peliculasIds);
}
