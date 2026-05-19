package cl.duoc.mi_lista_service.service;

import cl.duoc.mi_lista_service.model.ItemLista;
import cl.duoc.mi_lista_service.repository.ItemListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiListaService {

    @Autowired
    private ItemListaRepository itemListaRepository;

    public List<ItemLista> findAll() { return itemListaRepository.findAll(); }
    public ItemLista findById(Long id) { return itemListaRepository.findById(id).orElse(null); }
    public ItemLista save(ItemLista item) { return itemListaRepository.save(item); }
    public void delete(Long id) { itemListaRepository.deleteById(id); }

    public ItemLista update(Long id, ItemLista actualizado) {
        ItemLista existente = itemListaRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setIdUsuario(actualizado.getIdUsuario());
        existente.setIdPelicula(actualizado.getIdPelicula());
        existente.setFechaAgregado(actualizado.getFechaAgregado());
        return itemListaRepository.save(existente);
    }

    // REPORTES
    public List<ItemLista> findByUsuario(Long idUsuario) {
        return itemListaRepository.findByIdUsuario(idUsuario);
    }

    public Boolean existeEnLista(Long idUsuario, Long idPelicula) {
        return itemListaRepository.existsByIdUsuarioAndIdPelicula(idUsuario, idPelicula);
    }

    public Long cantidadUsuariosConPelicula(Long idPelicula) {
        return itemListaRepository.findAll().stream()
                .filter(i -> i.getIdPelicula().equals(idPelicula))
                .count();
    }
}