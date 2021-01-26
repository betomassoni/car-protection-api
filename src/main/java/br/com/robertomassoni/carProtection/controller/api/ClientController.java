package br.com.robertomassoni.carProtection.controller.api;

import br.com.robertomassoni.carProtection.dto.response.Response;
import br.com.robertomassoni.carProtection.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @GetMapping("/client")
    public Response getAllClient(Pageable pageable) {
        return Response.ok().setContent(clientService.getClients());
    } 
    
    @GetMapping("/client/{id}")
    public Response getClient(@PathVariable("id") String id) {
        return Response.ok().setContent(clientService.getClient(id));
    } 
}