package br.com.robertomassoni.carProtection.controller.api;

import javax.validation.Valid;
import br.com.robertomassoni.carProtection.dto.mapper.ClientMapper;
import br.com.robertomassoni.carProtection.dto.response.Response;
import br.com.robertomassoni.carProtection.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.robertomassoni.carProtection.controller.request.ClientRequest;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public Response getAllClient(Pageable pageable) {
        return Response.ok().setContent(clientService.getClients());
    }

    @GetMapping("/{id}")
    public Response getClient(@PathVariable("id") String id) {
        return Response.ok().setContent(clientService.getClient(id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") String id) {
        return Response.ok().setContent(clientService.deleteClient(id));
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") String id, @RequestBody ClientRequest clientRequest) {
        return Response.ok().setContent(clientService.updateClient(id, ClientMapper.toClientDto(clientRequest)));
    }

    @PostMapping()
    public Response save(@RequestBody @Valid ClientRequest clientRequest) {
        return Response.ok().setContent(clientService.saveClient(ClientMapper.toClientDto(clientRequest)));
    }
}
