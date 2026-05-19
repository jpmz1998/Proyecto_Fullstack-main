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
import java.util.stream.Collectors;

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
    public Productora update(Long id, Productora actualizada) {
        Productora existente = productoraRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setNombre(actualizada.getNombre());
        existente.setPaisUbicacion(actualizada.getPaisUbicacion());
        existente.setAnioFundacion(actualizada.getAnioFundacion());
        return productoraRepository.save(existente);
    }

    public ProductoraDTO obtenerEstadisticasProductora(Long idProductora) {
        Productora local = productoraRepository.findById(idProductora).orElse(null);
        if (local == null) return null;
        ProductoraDTO dto = mapper.toDTO(local);
        try {
            List<Long> ids = peliculaClient.obtenerIdsPeliculasPorProductora(idProductora);
            if (ids != null) dto.setCantidadPeliculasFinanciadas(ids.size());
        } catch (Exception e) {
            dto.setCantidadPeliculasFinanciadas(0);
        }
        return dto;
    }

    // REPORTES
    public List<ProductoraDTO> findByPais(String pais) {
        return productoraRepository.findByPaisUbicacionIgnoreCase(pais)
                .stream()
                .map(p -> obtenerEstadisticasProductora(p.getId()))
                .collect(Collectors.toList());
    }

    public List<ProductoraDTO> findByRangoAnio(Integer anioMin, Integer anioMax) {
        return productoraRepository.findByRangoAnioFundacion(anioMin, anioMax)
                .stream()
                .map(p -> obtenerEstadisticasProductora(p.getId()))
                .collect(Collectors.toList());
    }

    public List<ProductoraDTO> findByNombre(String nombre) {
        return productoraRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(p -> obtenerEstadisticasProductora(p.getId()))
                .collect(Collectors.toList());
    }
}