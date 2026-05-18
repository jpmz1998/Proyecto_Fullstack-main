package cl.duoc.productora_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pelicula-service")
public interface PeliculaFeignClient {

    // Le preguntaremos a la película que IDs están asociados a esta productora
    @GetMapping("/api/v1/peliculas/productora/{idProductora}/ids")
    List<Long> obtenerIdsPeliculasPorProductora(@PathVariable("idProductora") Long idProductora);
}