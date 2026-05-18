package cl.duoc.recomendacion_service.repository;

import cl.duoc.recomendacion_service.model.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {

    // Metodo custom: Buscar todas las recomendaciones de un usuario específico
    List<Recomendacion> findByIdUsuario(Long idUsuario);
}
