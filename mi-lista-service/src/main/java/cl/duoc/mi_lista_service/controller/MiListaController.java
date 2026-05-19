package cl.duoc.mi_lista_service.controller;

import cl.duoc.mi_lista_service.dto.ItemListaDTO;
import cl.duoc.mi_lista_service.mapper.ItemListaMapper;
import cl.duoc.mi_lista_service.model.ItemLista;
import cl.duoc.mi_lista_service.service.MiListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/mi-lista")
public class MiListaController {

    @Autowired
    private MiListaService miListaService;

    @Autowired
    private ItemListaMapper mapper;

    // CRUD
    @GetMapping
    public ResponseEntity<List<ItemListaDTO>> listar() {
        return ResponseEntity.ok(miListaService.findAll().stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemListaDTO> obtenerPorId(@PathVariable Long id) {
        ItemLista item = miListaService.findById(id);
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(item));
    }

    @PostMapping
    public ResponseEntity<ItemListaDTO> agregar(@RequestBody ItemLista item) {
        return ResponseEntity.ok(mapper.toDTO(miListaService.save(item)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemListaDTO> actualizar(@PathVariable Long id, @RequestBody ItemLista item) {
        ItemLista actualizado = miListaService.update(id, item);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        miListaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ItemListaDTO>> porUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(miListaService.findByUsuario(idUsuario).stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeEnLista(
            @RequestParam Long idUsuario,
            @RequestParam Long idPelicula) {
        return ResponseEntity.ok(miListaService.existeEnLista(idUsuario, idPelicula));
    }

    @GetMapping("/pelicula/{idPelicula}/cantidad")
    public ResponseEntity<Long> cantidadUsuariosConPelicula(@PathVariable Long idPelicula) {
        return ResponseEntity.ok(miListaService.cantidadUsuariosConPelicula(idPelicula));
    }
}
