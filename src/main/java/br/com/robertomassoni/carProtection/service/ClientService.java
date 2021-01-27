package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.dto.model.ClientDto;
import java.util.List;

public interface ClientService {
    void deleteAllClients();
    List<ClientDto> getClients();
    ClientDto saveClient(ClientDto client);
    ClientDto getClient(String id);
    ClientDto deleteClient(String id);
    ClientDto updateClient(String id, ClientDto clientDto);    
}
