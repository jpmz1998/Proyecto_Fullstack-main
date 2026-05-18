package cl.duoc.usuarios_service.config;

import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Validamos si la base de datos está vacía para no duplicar datos
        if (usuarioRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE USUARIOS ***");

            // Respetamos las validaciones: nombre > 3, password > 6, formato email
            Usuario u1 = new Usuario(null, "Pedro Pascal", "pedro@netflix.com", "123456", "ADMIN");
            Usuario u2 = new Usuario(null, "Juan Perez", "juan@gmail.com", "sixseven", "CLIENTE");
            Usuario u3 = new Usuario(null, "Diego Gomez", "diego@gmail.com", "666777", "CLIENTE");

            usuarioRepository.save(u1);
            usuarioRepository.save(u2);
            usuarioRepository.save(u3);

            System.out.println("*** DATOS CARGADOS CORRECTAMENTE ***");
        }
    }
}