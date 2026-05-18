package cl.duoc.productora_service.repository;

import cl.duoc.productora_service.model.Productora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoraRepository extends JpaRepository<Productora, Long> {
}
