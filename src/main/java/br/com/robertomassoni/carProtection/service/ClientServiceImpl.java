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
import br.com.robertomassoni.carProtection.exception.CarProtectionException;
import static br.com.robertomassoni.carProtection.enumerator.EntityType.*;
import static br.com.robertomassoni.carProtection.enumerator.ExceptionType.*;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void deleteAllClients() {
        try {
            clientRepository.deleteAll();
        } catch (Exception ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_EXCEPTION);
        }
    }

    @Override
    public List<ClientDto> getClients() {
        try {
            return ClientMapper.toClientDto(clientRepository.findAll());
        } catch (Exception ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_EXCEPTION);
        }
    }

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        try {
            if (clientDto == null) {
                throw new CarProtectionException.EntityIsEmptyException();
            }
            if (cpfExists(clientDto.getCpf())) {
                throw new CarProtectionException.EntityAlreadyExistsException();
            }
            Client client = new Client()
                    .setName(clientDto.getName())
                    .setCpf(clientDto.getCpf())
                    .setCity(clientDto.getCity())
                    .setState(clientDto.getState());
            return ClientMapper.toClientDto(clientRepository.save(client));
        } catch (CarProtectionException.EntityAlreadyExistsException ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_ALREADY_EXISTS, clientDto.getCpf());
        } catch (CarProtectionException.EntityIsEmptyException ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_IS_EMPTY);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_EXCEPTION);
        }
    }

    @Override
    public ClientDto getClient(String id) {
        try {
            Optional<Client> clientOptional = clientRepository.findById(id);
            if (clientOptional.isPresent() == false) {
                throw new CarProtectionException.EntityNotFoundException();
            }
            return ClientMapper.toClientDto(clientOptional.get());
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_NOT_FOUND, id);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_EXCEPTION);
        }
    }

    @Override
    public ClientDto deleteClient(String id) {
        try {
            Optional<Client> clientOptional = clientRepository.findById(id);
            if (clientOptional.isPresent() == false) {
                throw new CarProtectionException.EntityNotFoundException();
            }
            clientRepository.delete(clientOptional.get());
            return ClientMapper.toClientDto(clientOptional.get());
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_NOT_FOUND, id);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_EXCEPTION);
        }
    }

    @Override
    public ClientDto updateClient(String id, ClientDto clientDto) {
        try {
            Optional<Client> existingClient = clientRepository.findById(id);
            if (existingClient.isPresent() == false) {
                throw new CarProtectionException.EntityNotFoundException();
            }
            Client changedClient = new Client()
                    .setCpf(clientDto.getCpf())
                    .setCity(clientDto.getCity())
                    .setState(clientDto.getState());

            // Merge both objects
            changedClient = ObjectUtil.merge(existingClient.get(), changedClient);
            changedClient = clientRepository.save(changedClient);
            return ClientMapper.toClientDto(changedClient);
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_NOT_FOUND, id);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_EXCEPTION);
        }
    }
    
    private boolean cpfExists(String cpf) {
        Optional<Client> clientOptional = Optional.ofNullable(clientRepository.findByCpf(cpf));        
        return clientOptional.isPresent();            
    }

}
