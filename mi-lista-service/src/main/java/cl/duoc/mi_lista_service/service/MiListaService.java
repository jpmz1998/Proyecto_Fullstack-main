package cl.duoc.mi_lista_service.service;

import cl.duoc.mi_lista_service.dto.ItemListaDTO;
import cl.duoc.mi_lista_service.mapper.ItemListaMapper;
import cl.duoc.mi_lista_service.model.ItemLista;
import cl.duoc.mi_lista_service.repository.ItemListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MiListaService {

    @Autowired
    private ItemListaRepository itemListaRepository;

    @Autowired
    private ItemListaMapper mapper;

    public List<ItemListaDTO> findAll() { return itemListaRepository.findAll().stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public ItemListaDTO findById(Long id) { return mapper.toDTO(itemListaRepository.findById(id).orElse(null)); }

    public ItemListaDTO save(ItemLista item) { return mapper.toDTO(itemListaRepository.save(item)); }

    public void delete(Long id) { itemListaRepository.deleteById(id); }

    public ItemListaDTO update(Long id, ItemLista actualizado) {
        ItemLista existente = itemListaRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setIdUsuario(actualizado.getIdUsuario());
        existente.setIdPelicula(actualizado.getIdPelicula());
        existente.setFechaAgregado(actualizado.getFechaAgregado());
        return mapper.toDTO(itemListaRepository.save(existente));
    }

    // REPORTES
    public List<ItemListaDTO> findByUsuario(Long idUsuario) {
        return itemListaRepository.findByIdUsuario(idUsuario).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
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