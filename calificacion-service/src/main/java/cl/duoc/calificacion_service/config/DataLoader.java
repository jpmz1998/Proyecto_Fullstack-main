package cl.duoc.calificacion_service.config;

import cl.duoc.calificacion_service.model.Calificacion;
import cl.duoc.calificacion_service.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (calificacionRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE CALIFICACIONES ***");
            calificacionRepository.save(new Calificacion(null, 7, LocalDate.now(), 1L, 1L));
            calificacionRepository.save(new Calificacion(null, 6, LocalDate.now(), 2L, 1L));
            calificacionRepository.save(new Calificacion(null, 5, LocalDate.now(), 1L, 2L));
            calificacionRepository.save(new Calificacion(null, 7, LocalDate.now(), 3L, 3L));
            System.out.println("*** DATOS DE CALIFICACIONES CARGADOS CORRECTAMENTE ***");
        }
    }
}
