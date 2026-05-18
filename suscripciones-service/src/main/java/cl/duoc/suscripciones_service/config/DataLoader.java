package cl.duoc.suscripciones_service.config;

import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Validamos si la base de datos está vacía
        if (suscripcionRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE SUSCRIPCIONES ***");

            Suscripcion sub1 = new Suscripcion(null, "Premium", 10000, LocalDate.now(), "ACTIVA", 1L);
            Suscripcion sub2 = new Suscripcion(null, "Basico", 5000, LocalDate.now().minusMonths(1), "INACTIVA", 2L);
            Suscripcion sub3 = new Suscripcion(null, "Estandar", 7500, LocalDate.now(), "ACTIVA", 3L);
            Suscripcion sub4 = new Suscripcion(null, "Extra Screen", 2000, LocalDate.now(), "ACTIVA", 1L);

            suscripcionRepository.save(sub1);
            suscripcionRepository.save(sub2);
            suscripcionRepository.save(sub3);
            suscripcionRepository.save(sub4);

            System.out.println("*** DATOS DE SUSCRIPCIONES CARGADOS CORRECTAMENTE ***");
        }
    }
}
