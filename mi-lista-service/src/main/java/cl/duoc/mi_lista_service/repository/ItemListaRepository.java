package cl.duoc.mi_lista_service.repository;

import cl.duoc.mi_lista_service.model.ItemLista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemListaRepository extends JpaRepository<ItemLista, Long> {

    // 1. Para buscar todas las películas guardadas por un usuario especifico
    List<ItemLista> findByIdUsuario(Long idUsuario);

    // 2. Para validar si un usuario ya guardo una película y no repetirla
    boolean existsByIdUsuarioAndIdPelicula(Long idUsuario, Long idPelicula);
}
