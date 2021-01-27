package br.com.robertomassoni.carProtection.repository;

import br.com.robertomassoni.carProtection.model.InsurancePolicy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends MongoRepository<InsurancePolicy, String> {

}
