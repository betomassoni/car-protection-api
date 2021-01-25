package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.model.Client;
import java.util.List;


public interface ClientService {
    List<Client> getClients();
    void deleteAllClients();
    Client saveClient(Client client);
}
