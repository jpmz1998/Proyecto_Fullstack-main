package cl.duoc.director_service.config;

import cl.duoc.director_service.model.Director;
import cl.duoc.director_service.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public void run(String... args) throws Exception {
        if (directorRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE DIRECTORES ***");
            directorRepository.save(new Director(null, "Christopher", "Nolan", LocalDate.of(1970, 7, 30), "Britanico"));
            directorRepository.save(new Director(null, "Steven", "Spielberg", LocalDate.of(1946, 12, 18), "Estadounidense"));
            directorRepository.save(new Director(null, "Martin", "Scorsese", LocalDate.of(1942, 11, 17), "Estadounidense"));
            System.out.println("*** DATOS DE DIRECTORES CARGADOS CORRECTAMENTE ***");
        }
    }
}
