package cl.duoc.usuarios_service.repository;

import cl.duoc.usuarios_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Al extender de JpaRepository, Spring ya sabe cómo hacer save(), findById(), findAll(), deleteById(), etc.
}