package cl.duoc.mi_lista_service.service;

import cl.duoc.mi_lista_service.model.ItemLista;
import cl.duoc.mi_lista_service.repository.ItemListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MiListaService {

    @Autowired
    private ItemListaRepository miListaRepository;

    // --- MÉTODOS DE LECTURA ---

    @Transactional(readOnly = true)
    public List<ItemLista> findAll() {
        return miListaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ItemLista findById(Long id) {
        return miListaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ItemLista> obtenerListaDeUsuario(Long idUsuario) {
        return miListaRepository.findByIdUsuario(idUsuario);
    }

    // --- MÉTODOS DE ESCRITURA (CRUD) ---

    @Transactional
    public ItemLista save(ItemLista item) {
        // Regla de Negocio: Evitar duplicados antes de guardar
        if (miListaRepository.existsByIdUsuarioAndIdPelicula(item.getIdUsuario(), item.getIdPelicula())) {
            throw new RuntimeException("La película ya está en la lista de este usuario.");
        }
        item.setFechaAgregado(LocalDate.now());
        return miListaRepository.save(item);
    }

    @Transactional
    public ItemLista update(Long id, ItemLista itemActualizado) {
        ItemLista itemExistente = miListaRepository.findById(id).orElse(null);
        if (itemExistente == null) return null;

        // Si se intenta cambiar la película, validamos que no exista ya en la lista del usuario
        if (!itemExistente.getIdPelicula().equals(itemActualizado.getIdPelicula())) {
            if (miListaRepository.existsByIdUsuarioAndIdPelicula(itemExistente.getIdUsuario(), itemActualizado.getIdPelicula())) {
                throw new RuntimeException("No se puede actualizar: la nueva película ya existe en la lista.");
            }
        }

        itemExistente.setIdPelicula(itemActualizado.getIdPelicula());
        itemExistente.setIdUsuario(itemActualizado.getIdUsuario());
        // La fecha de agregado solemos mantenerla original o actualizarla según el requerimiento

        return miListaRepository.save(itemExistente);
    }

    @Transactional
    public void delete(Long id) {
        miListaRepository.deleteById(id);
    }
}
