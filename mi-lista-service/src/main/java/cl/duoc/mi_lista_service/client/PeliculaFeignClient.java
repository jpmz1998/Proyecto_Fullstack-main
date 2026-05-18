package cl.duoc.mi_lista_service.client;

import cl.duoc.mi_lista_service.dto.PeliculaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pelicula-service")
public interface PeliculaFeignClient {

    @GetMapping("/api/v1/peliculas/{id}")
    PeliculaDTO obtenerPorId(@PathVariable("id") Long id);
}
