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

    // CRUD
    @GetMapping
    public ResponseEntity<List<ProductoraDTO>> listarTodas() {
        return ResponseEntity.ok(productoraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoraDTO> obtenerPorId(@PathVariable Long id) {
        ProductoraDTO dto = productoraService.obtenerEstadisticasProductora(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductoraDTO> crear(@Valid @RequestBody Productora productora) {
        return ResponseEntity.ok(productoraService.save(productora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoraDTO> actualizar(@PathVariable Long id, @Valid @RequestBody Productora productora) {
        ProductoraDTO actualizada = productoraService.update(id, productora);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/filtro/pais")
    public ResponseEntity<List<ProductoraDTO>> filtrarPorPais(@RequestParam String pais) {
        return ResponseEntity.ok(productoraService.findByPais(pais));
    }

    @GetMapping("/filtro/anio")
    public ResponseEntity<List<ProductoraDTO>> filtrarPorRangoAnio(
            @RequestParam Integer anioMin,
            @RequestParam Integer anioMax) {
        return ResponseEntity.ok(productoraService.findByRangoAnio(anioMin, anioMax));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ProductoraDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoraService.findByNombre(nombre));
    }
}
