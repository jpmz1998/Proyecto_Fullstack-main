package cl.duoc.pelicula_service.repository;

import cl.duoc.pelicula_service.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    // (Para el director)
    @Query("SELECT p.id FROM Pelicula p WHERE p.directorId = :directorId")
    List<Long> findIdsByDirectorId(@Param("directorId") Long directorId);

    // --- ¡NUEVA! (Para la productora) ---
    @Query("SELECT p.id FROM Pelicula p WHERE p.productoraId = :productoraId")
    List<Long> findIdsByProductoraId(@Param("productoraId") Long productoraId);

    // --- ¡NUEVO FILTRO DE EDAD! ---
    // Spring traduce esto como: "SELECT * FROM Pelicula WHERE esPara18 = ?"
    List<Pelicula> findByEsPara18(Boolean esPara18);
}
