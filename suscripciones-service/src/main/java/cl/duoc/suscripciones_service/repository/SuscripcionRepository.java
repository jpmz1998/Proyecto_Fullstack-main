package cl.duoc.suscripciones_service.repository;

import cl.duoc.suscripciones_service.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

    // Reporte 1: Filtrar por estado
    List<Suscripcion> findByEstado(String estado);

    // Reporte 2: Filtrar por tipo de plan
    List<Suscripcion> findByTipoPlan(String tipoPlan);

    // Reporte 3: Suscripciones de un usuario específico
    List<Suscripcion> findByUsuarioId(Long usuarioId);

    // Reporte 4: Filtrar por rango de monto
    @Query("SELECT s FROM Suscripcion s WHERE s.monto BETWEEN :montoMin AND :montoMax")
    List<Suscripcion> findByRangoDeMonto(@Param("montoMin") Integer montoMin, @Param("montoMax") Integer montoMax);
}