package cl.duoc.productora_service.controller;

import cl.duoc.productora_service.dto.ProductoraDTO;
import cl.duoc.productora_service.mapper.ProductoraMapper;
import cl.duoc.productora_service.model.Productora;
import cl.duoc.productora_service.service.ProductoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/productoras")
public class ProductoraController {

    @Autowired
    private ProductoraService productoraService;

    @Autowired
    private ProductoraMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductoraDTO>> listarTodas() {
        List<ProductoraDTO> lista = productoraService.findAll()
                .stream()
                .map(p -> productoraService.obtenerEstadisticasProductora(p.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoraDTO> obtenerPorId(@PathVariable Long id) {
        ProductoraDTO dto = productoraService.obtenerEstadisticasProductora(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductoraDTO> crear(@Valid @RequestBody Productora productora) {
        Productora nuevaProductora = productoraService.save(productora);
        return ResponseEntity.ok(mapper.toDTO(nuevaProductora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoraDTO> actualizar(@PathVariable Long id, @Valid @RequestBody Productora productora) {
        Productora productoraActualizada = productoraService.update(id, productora);
        if (productoraActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(productoraActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/estadisticas")
    public ResponseEntity<ProductoraDTO> obtenerEstadisticas(@PathVariable Long id) {
        ProductoraDTO dto = productoraService.obtenerEstadisticasProductora(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
