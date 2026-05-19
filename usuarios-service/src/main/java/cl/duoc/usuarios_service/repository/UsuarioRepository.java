package cl.duoc.usuarios_service.repository;

import cl.duoc.usuarios_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Reporte 1: Buscar por email exacto
    Optional<Usuario> findByEmail(String email);

    // Reporte 2: Filtrar por rol
    List<Usuario> findByRol(String rol);

    // Reporte 3: Buscar por nombre parcial
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}