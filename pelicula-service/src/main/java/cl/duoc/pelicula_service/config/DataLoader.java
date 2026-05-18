package cl.duoc.pelicula_service.config;

import cl.duoc.pelicula_service.model.Pelicula;
import cl.duoc.pelicula_service.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (peliculaRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE PELICULAS ***");
            // directorId y productoraId hacen referencia a los IDs del DataLoader de director y productora
            peliculaRepository.save(new Pelicula(null, "Inception", LocalDate.of(2010, 7, 16), "Ciencia Ficcion", "Un ladron que roba secretos", 1L, 1L, 148, false));
            peliculaRepository.save(new Pelicula(null, "Jurassic Park", LocalDate.of(1993, 6, 11), "Aventura", "Dinosaurios en un parque", 2L, 2L, 127, false));
            peliculaRepository.save(new Pelicula(null, "Goodfellas", LocalDate.of(1990, 9, 19), "Drama", "La vida de la mafia", 3L, 3L, 145, true));
            System.out.println("*** DATOS DE PELICULAS CARGADOS CORRECTAMENTE ***");
        }
    }
}
