package cl.duoc.director_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "calificacion-service")
public interface CalificacionFeignClient {

    // Le enviamos una lista de IDs de películas y nos devuelve el promedio matemático
    @PostMapping("/api/v1/calificaciones/promedio-masivo")
    Double obtenerPromedioPorListaDePeliculas(@RequestBody List<Long> peliculasIds);
}