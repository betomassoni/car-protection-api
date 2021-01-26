package br.com.robertomassoni.carProtection;

import br.com.robertomassoni.carProtection.service.ClientService;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.robertomassoni.carProtection.dto.model.ClientDto;

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
            
            ClientDto c1 = new ClientDto();
            c1.setName("Maria");
            c1.setCpf("12345678909");
            c1.setCity("São Paulo");
            c1.setState("SP");
            
            ClientDto c2 = new ClientDto();
            c2.setName("João");
            c2.setCpf("98765432109");
            c2.setCity("Rio de Janeiro");
            c2.setState("RJ");
            
            List<ClientDto> clientDtoList = Arrays.asList(c1, c2);
            
            clientDtoList.forEach(clientDto -> clientService.saveClient(clientDto));
            clientService.getClients().forEach(clientsList -> log.info("CLIENT --> " + clientsList.getName()));
        };
    }
}
