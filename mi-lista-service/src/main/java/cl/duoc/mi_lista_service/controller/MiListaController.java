package cl.duoc.mi_lista_service.controller;

import cl.duoc.mi_lista_service.dto.ItemListaDTO;
import cl.duoc.mi_lista_service.mapper.ItemListaMapper;
import cl.duoc.mi_lista_service.model.ItemLista;
import cl.duoc.mi_lista_service.service.MiListaService;
import jakarta.validation.Valid;
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

    // 1. Listar TODOS los items de todas las listas (Admin)
    @GetMapping
    public ResponseEntity<List<ItemListaDTO>> listarTodo() {
        List<ItemListaDTO> lista = miListaService.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // 2. Obtener un item específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemListaDTO> obtenerPorId(@PathVariable Long id) {
        ItemLista item = miListaService.findById(id);
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(item));
    }

    // 3. Obtener la lista específica de UN usuario  // METODO CUSTOM
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ItemListaDTO>> obtenerListaUsuario(@PathVariable Long idUsuario) {
        List<ItemListaDTO> listaDto = miListaService.obtenerListaDeUsuario(idUsuario)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    // 4. Crear / Agregar a la lista
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ItemLista item) {
        try {
            ItemLista nuevo = miListaService.save(item);
            return ResponseEntity.ok(mapper.toDTO(nuevo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. Actualizar un item de la lista
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ItemLista item) {
        try {
            ItemLista actualizado = miListaService.update(id, item);
            if (actualizado == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(mapper.toDTO(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 6. Eliminar de la lista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        miListaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
