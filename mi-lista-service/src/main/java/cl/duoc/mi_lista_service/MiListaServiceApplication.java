package cl.duoc.mi_lista_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MiListaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MiListaServiceApplication.class, args);
	}
}
