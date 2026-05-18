package cl.duoc.suscripciones_service.client;

import cl.duoc.suscripciones_service.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-service")
public interface UsuarioFeignClient {

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id);
}