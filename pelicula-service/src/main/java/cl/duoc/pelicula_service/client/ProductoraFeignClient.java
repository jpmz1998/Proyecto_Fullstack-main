package cl.duoc.pelicula_service.client;

import cl.duoc.pelicula_service.dto.ProductoraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productora-service")
public interface ProductoraFeignClient {

    @GetMapping("/api/v1/productoras/{id}")
    ProductoraDTO obtenerPorId(@PathVariable("id") Long id);
}