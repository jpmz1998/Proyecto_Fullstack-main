package cl.duoc.productora_service.config;

import cl.duoc.productora_service.model.Productora;
import cl.duoc.productora_service.repository.ProductoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoraRepository productoraRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productoraRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE PRODUCTORAS ***");
            productoraRepository.save(new Productora(null, "Warner Bros", "Estados Unidos", 1923));
            productoraRepository.save(new Productora(null, "Universal Pictures", "Estados Unidos", 1912));
            productoraRepository.save(new Productora(null, "Paramount Pictures", "Estados Unidos", 1912));
            System.out.println("*** DATOS DE PRODUCTORAS CARGADOS CORRECTAMENTE ***");
        }
    }
}
