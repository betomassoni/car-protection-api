package br.com.robertomassoni.carProtection;

import br.com.robertomassoni.carProtection.service.ClientService;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.robertomassoni.carProtection.model.Client;

@SpringBootApplication
@Slf4j
public class CarProtectionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarProtectionApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(ClientService clientService) {
        return (args) -> {
            log.info("Generating sample data");
            clientService.deleteAllClients();
            List<String> clients = Arrays.asList("Maria", "João");
            clients.forEach(client -> clientService.saveClient(new Client(client)));

            clientService.getClients().forEach(clientsList
                    -> log.info("CLIENT --> " + clientsList.getName()));
        };
    }
}
