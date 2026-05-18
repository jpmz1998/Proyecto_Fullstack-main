package cl.duoc.director_service.controller;

import cl.duoc.director_service.dto.DirectorDTO;
import cl.duoc.director_service.model.Director;
import cl.duoc.director_service.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/directores")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<DirectorDTO>> listarTodos() {
        List<DirectorDTO> directores = directorService.findAll();
        return new ResponseEntity<>(directores, HttpStatus.OK);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> buscarPorId(@PathVariable Long id) {
        DirectorDTO director = directorService.findById(id);
        if (director == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    // CREAR DIRECTOR
    @PostMapping
    public ResponseEntity<DirectorDTO> crear(@Valid @RequestBody Director director) {
        DirectorDTO nuevoDirector = directorService.save(director);
        return new ResponseEntity<>(nuevoDirector, HttpStatus.CREATED);
    }

    // ELIMINAR DIRECTOR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            directorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody Director directorDetails) {
        DirectorDTO actualizado = directorService.update(id, directorDetails);

        if (actualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
}

