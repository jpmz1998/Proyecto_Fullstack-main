package cl.duoc.pelicula_service.client;

import cl.duoc.pelicula_service.dto.DirectorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "director-service")
public interface DirectorFeignClient {

    @GetMapping("/api/v1/directores/{id}")
    DirectorDTO obtenerPorId(@PathVariable("id") Long id);


    //se usa para solicitar los datos de director
}
