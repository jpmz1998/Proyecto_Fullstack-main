package cl.duoc.director_service.repository;

import cl.duoc.director_service.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director,Long> {




}
