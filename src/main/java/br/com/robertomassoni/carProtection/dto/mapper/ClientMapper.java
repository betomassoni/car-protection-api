package br.com.robertomassoni.carProtection.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import br.com.robertomassoni.carProtection.dto.model.ClientDto;
import br.com.robertomassoni.carProtection.model.Client;
import br.com.robertomassoni.carProtection.controller.request.ClientRequest;

public class ClientMapper {
        
    public static ClientDto toClientDto(Client client) {
        ClientDto clientDto = null;
        if (client != null) {                        
            clientDto = new ClientDto()
                    .setId(client.getId())
                    .setName(client.getName())
                    .setCpf(client.getCpf())
                    .setCity(client.getCity())
                    .setState(client.getState());            
        }
        return clientDto;
    }
    
    public static List<ClientDto> toClientDto(List<Client> clientList) {
        ArrayList<ClientDto> quoteDtoList = new ArrayList();

        clientList.stream().forEach((client) -> {
            quoteDtoList.add(toClientDto(client));
        });

        return quoteDtoList;
    }
    
    public static ClientDto toClientDto(ClientRequest request) {
        ClientDto clientDto = null;
        if (request != null) {
            clientDto = new ClientDto()
                    .setCpf(request.getCpf())
                    .setCity(request.getCity())
                    .setState(request.getState()); 
        }
        return clientDto;
    }
    
    
}
