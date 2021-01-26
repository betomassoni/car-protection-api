package br.com.robertomassoni.carProtection.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import br.com.robertomassoni.carProtection.dto.model.ClientDto;
import br.com.robertomassoni.carProtection.model.Client;

public class ClientMapper {
        
    public static ClientDto toClientDto(Client client) {
        ClientDto clientDto = null;
        if (client != null) {
            clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(client.getName());
            clientDto.setCpf(client.getCpf());
            clientDto.setCity(client.getCity());
            clientDto.setState(client.getState());
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
    
}
