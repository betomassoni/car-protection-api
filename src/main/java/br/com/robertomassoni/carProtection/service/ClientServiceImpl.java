package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.model.Client;
import br.com.robertomassoni.carProtection.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

}
