package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.dto.mapper.ClientMapper;
import br.com.robertomassoni.carProtection.dto.model.ClientDto;
import br.com.robertomassoni.carProtection.model.Client;
import br.com.robertomassoni.carProtection.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    @Override
    public List<ClientDto> getClients() {
        return ClientMapper.toClientDto(clientRepository.findAll());
    }

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        Client client = Client
                .builder()
                .name(clientDto.getName())
                .cpf(clientDto.getCpf())
                .city(clientDto.getCity())
                .state(clientDto.getState())
                .build();
        return ClientMapper.toClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDto getClient(String id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            return ClientMapper.toClientDto(clientOptional.get());            
        }
        throw new RuntimeException("Erro");        
    }

    @Override
    public ClientDto deleteClient(String id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.delete(clientOptional.get());
            return ClientMapper.toClientDto(clientOptional.get());
        } else {
            throw new RuntimeException("Erro");            
        }
    }

}
