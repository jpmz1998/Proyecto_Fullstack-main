package cl.duoc.director_service.controller;

import cl.duoc.director_service.dto.DirectorDTO;
import cl.duoc.director_service.model.Director;
import cl.duoc.director_service.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/directores")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    // CRUD
    @GetMapping
    public ResponseEntity<List<DirectorDTO>> listar() {
        return ResponseEntity.ok(directorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> obtenerPorId(@PathVariable Long id) {
        DirectorDTO dto = directorService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> crear(@RequestBody Director director) {
        return new ResponseEntity<>(directorService.save(director), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> actualizar(@PathVariable Long id, @RequestBody Director director) {
        DirectorDTO actualizado = directorService.update(id, director);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        directorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/filtro/nacionalidad")
    public ResponseEntity<List<DirectorDTO>> filtrarPorNacionalidad(@RequestParam String nacionalidad) {
        return ResponseEntity.ok(directorService.findByNacionalidad(nacionalidad));
    }

    @GetMapping("/buscar/apellido")
    public ResponseEntity<List<DirectorDTO>> buscarPorApellido(@RequestParam String apellido) {
        return ResponseEntity.ok(directorService.findByApellido(apellido));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DirectorDTO>> buscarPorNombreYApellido(
            @RequestParam String nombre,
            @RequestParam String apellido) {
        return ResponseEntity.ok(directorService.findByNombreYApellido(nombre, apellido));
    }
}
