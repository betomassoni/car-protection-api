package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.dto.mapper.ClientMapper;
import br.com.robertomassoni.carProtection.dto.model.ClientDto;
import br.com.robertomassoni.carProtection.model.Client;
import br.com.robertomassoni.carProtection.repository.ClientRepository;
import br.com.robertomassoni.carProtection.util.ObjectUtil;
import java.util.List;
import java.util.Optional;
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
        Client client = new Client()
                .setName(clientDto.getName())
                .setCpf(clientDto.getCpf())
                .setCity(clientDto.getCity())
                .setState(clientDto.getState());
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

    @Override
    public ClientDto updateClient(String id, ClientDto clientDto) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            Client changedClient = new Client()
                    .setCpf(clientDto.getCpf())
                    .setCity(clientDto.getCity())
                    .setState(clientDto.getState());
        
            try {
                changedClient = ObjectUtil.merge(existingClient.get(), changedClient);
            } catch (Exception ex) {            
            }
            

            changedClient = clientRepository.save(changedClient);
            return ClientMapper.toClientDto(changedClient);
        } else {
            throw new RuntimeException("Erro");
        }
    }

}
