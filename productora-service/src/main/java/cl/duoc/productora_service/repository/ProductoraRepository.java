package cl.duoc.productora_service.repository;

import cl.duoc.productora_service.model.Productora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoraRepository extends JpaRepository<Productora, Long> {

    // Reporte 1: Filtrar por país
    List<Productora> findByPaisUbicacionIgnoreCase(String paisUbicacion);

    // Reporte 2: Filtrar por rango de año de fundación
    @Query("SELECT p FROM Productora p WHERE p.anioFundacion BETWEEN :anioMin AND :anioMax")
    List<Productora> findByRangoAnioFundacion(@Param("anioMin") Integer anioMin, @Param("anioMax") Integer anioMax);

    // Reporte 3: Buscar por nombre
    List<Productora> findByNombreContainingIgnoreCase(String nombre);
}
