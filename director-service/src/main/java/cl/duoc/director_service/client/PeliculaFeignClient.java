package cl.duoc.director_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pelicula-service")
public interface PeliculaFeignClient {

    // Le pedimos al catálogo: "Entrégame solo los IDs de las películas de este director"
    @GetMapping("/api/v1/peliculas/director/{idDirector}/ids")
    List<Long> obtenerIdsPeliculasPorDirector(@PathVariable("idDirector") Long idDirector);
}