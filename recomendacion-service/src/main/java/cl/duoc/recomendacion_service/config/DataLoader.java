package cl.duoc.recomendacion_service.config;

import cl.duoc.recomendacion_service.model.Recomendacion;
import cl.duoc.recomendacion_service.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (recomendacionRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE RECOMENDACIONES ***");
            recomendacionRepository.save(new Recomendacion(null, 1L, 2L, "Te encantara esta pelicula", 95, LocalDate.now()));
            recomendacionRepository.save(new Recomendacion(null, 2L, 1L, "Basada en tu historial de aventuras", 80, LocalDate.now()));
            recomendacionRepository.save(new Recomendacion(null, 3L, 3L, "Pelicula aclamada por la critica", 90, LocalDate.now()));
            System.out.println("*** DATOS DE RECOMENDACIONES CARGADOS CORRECTAMENTE ***");
        }
    }
}
