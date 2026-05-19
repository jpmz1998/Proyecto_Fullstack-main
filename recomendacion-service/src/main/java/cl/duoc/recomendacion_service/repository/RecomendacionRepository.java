package cl.duoc.recomendacion_service.repository;

import cl.duoc.recomendacion_service.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {

    // Reporte 1: Recomendaciones de un usuario
    List<Recomendacion> findByIdUsuario(Long idUsuario);

    // Reporte 2: Filtrar por nivel de confianza mínimo
    List<Recomendacion> findByNivelConfianzaGreaterThanEqual(Integer nivelMinimo);

    // Reporte 3: Recomendaciones de una película específica
    List<Recomendacion> findByIdPelicula(Long idPelicula);
}
