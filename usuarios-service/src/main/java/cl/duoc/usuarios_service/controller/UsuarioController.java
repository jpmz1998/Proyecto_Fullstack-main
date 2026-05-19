package cl.duoc.usuarios_service.controller;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // CRUD
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO dto = usuarioService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        UsuarioDTO actualizado = usuarioService.update(id, usuario);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/buscar/email")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@RequestParam String email) {
        UsuarioDTO dto = usuarioService.findByEmail(email);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro/rol")
    public ResponseEntity<List<UsuarioDTO>> filtrarPorRol(@RequestParam String rol) {
        return ResponseEntity.ok(usuarioService.findByRol(rol));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<UsuarioDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.findByNombre(nombre));
    }
}
