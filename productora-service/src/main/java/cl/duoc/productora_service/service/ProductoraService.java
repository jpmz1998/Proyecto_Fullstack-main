package cl.duoc.productora_service.service;

import cl.duoc.productora_service.client.PeliculaFeignClient;
import cl.duoc.productora_service.dto.ProductoraDTO;
import cl.duoc.productora_service.mapper.ProductoraMapper;
import cl.duoc.productora_service.model.Productora;
import cl.duoc.productora_service.repository.ProductoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoraService {

    @Autowired
    private ProductoraRepository productoraRepository;

    @Autowired
    private ProductoraMapper mapper;

    @Autowired
    private PeliculaFeignClient peliculaClient;

    @Transactional(readOnly = true)
    public List<Productora> findAll() {
        return productoraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Productora findById(Long id) {
        return productoraRepository.findById(id).orElse(null);
    }

    @Transactional
    public Productora save(Productora productora) {
        return productoraRepository.save(productora);
    }

    @Transactional
    public void delete(Long id) {
        productoraRepository.deleteById(id);
    }

    @Transactional
    public Productora update(Long id, Productora productoraActualizada) {
        Productora productoraExistente = productoraRepository.findById(id).orElse(null);
        if (productoraExistente == null) return null;

        productoraExistente.setNombre(productoraActualizada.getNombre());
        productoraExistente.setPaisUbicacion(productoraActualizada.getPaisUbicacion());
        productoraExistente.setAnioFundacion(productoraActualizada.getAnioFundacion());

        return productoraRepository.save(productoraExistente);
    }

    // --- EL METODO CUSTOM DE ORQUESTACION ---
    public ProductoraDTO obtenerEstadisticasProductora(Long idProductora) {
        // 1. Buscamos la productora localmente
        Productora productoraLocal = productoraRepository.findById(idProductora).orElse(null);
        if (productoraLocal == null) return null;

        // 2. Mapeamos a DTO
        ProductoraDTO dto = mapper.toDTO(productoraLocal);

        try {
            // 3. Llamada Feign al catálogo
            List<Long> idsPeliculas = peliculaClient.obtenerIdsPeliculasPorProductora(idProductora);

            if (idsPeliculas != null) {
                dto.setCantidadPeliculasFinanciadas(idsPeliculas.size());
            }
        } catch (Exception e) {
            System.err.println("Error de comunicación con pelicula-service: " + e.getMessage());
            dto.setCantidadPeliculasFinanciadas(0); // Valor por defecto si hay error
        }

        return dto;
    }
}