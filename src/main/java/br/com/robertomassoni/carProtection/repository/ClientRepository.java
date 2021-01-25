package br.com.robertomassoni.carProtection.repository;

import br.com.robertomassoni.carProtection.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
