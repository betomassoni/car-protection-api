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
import br.com.robertomassoni.carProtection.service.InsurancePolicyService;
import br.com.robertomassoni.carProtection.dto.model.InsurancePolicyDto;
import java.util.Date;

@SpringBootApplication
@Slf4j
public class CarProtectionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarProtectionApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner mockSetup(ClientService clientService, InsurancePolicyService insurancePolicyService) {
        return (args) -> {
            log.info("Generating sample data");
            addClient(clientService);
            addInsurancePolicy(insurancePolicyService, clientService);
        };
    }

    private void addClient(ClientService clientService) {
        clientService.deleteAllClients();

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
    }
    
    private void addInsurancePolicy(InsurancePolicyService insurancePolicyService, ClientService clientService) {
        insurancePolicyService.deleteAllInsurancePolicies();
        
        List<ClientDto> clientList = clientService.getClients();
        
        
        ClientDto c1 = clientList.get(0);        
        
        

        InsurancePolicyDto insurancePolicyDto = new InsurancePolicyDto();
        insurancePolicyDto.setNumber("9776672");
        insurancePolicyDto.setPlate("RH7HUEE");
        insurancePolicyDto.setBegin("2021-01-01");
        insurancePolicyDto.setEnd("2022-01-01");
        insurancePolicyDto.setAmount(110.90);
        insurancePolicyDto.setClientId(c1.getId());

        insurancePolicyService.saveInsurancePolicy(insurancePolicyDto);
        
        log.info("INSURANCE POLICY --> " + insurancePolicyDto.getNumber());
        
    }
}
