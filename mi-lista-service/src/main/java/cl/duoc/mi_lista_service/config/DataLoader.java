package cl.duoc.mi_lista_service.config;

import cl.duoc.mi_lista_service.model.ItemLista;
import cl.duoc.mi_lista_service.repository.ItemListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ItemListaRepository itemListaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (itemListaRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA DE MI LISTA ***");
            itemListaRepository.save(new ItemLista(null, 1L, 1L, LocalDate.now()));
            itemListaRepository.save(new ItemLista(null, 1L, 2L, LocalDate.now()));
            itemListaRepository.save(new ItemLista(null, 2L, 3L, LocalDate.now()));
            System.out.println("*** DATOS DE MI LISTA CARGADOS CORRECTAMENTE ***");
        }
    }
}
