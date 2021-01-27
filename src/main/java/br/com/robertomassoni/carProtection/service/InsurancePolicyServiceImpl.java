package br.com.robertomassoni.carProtection.service;

import br.com.robertomassoni.carProtection.dto.mapper.InsurancePolicyMapper;
import br.com.robertomassoni.carProtection.dto.model.InsurancePolicyDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.robertomassoni.carProtection.repository.InsurancePolicyRepository;
import br.com.robertomassoni.carProtection.exception.CarProtectionException;
import static br.com.robertomassoni.carProtection.enumerator.EntityType.*;
import static br.com.robertomassoni.carProtection.enumerator.ExceptionType.*;
import br.com.robertomassoni.carProtection.model.InsurancePolicy;
import br.com.robertomassoni.carProtection.model.Client;
import br.com.robertomassoni.carProtection.repository.ClientRepository;
import br.com.robertomassoni.carProtection.util.ObjectUtil;
import java.util.Optional;

@Component
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void deleteAllInsurancePolicies() {
        try {
            insurancePolicyRepository.deleteAll();
        } catch (Exception ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_EXCEPTION);
        }
    }

    @Override
    public List<InsurancePolicyDto> getInsurancePolicies() {
        try {
            return InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyRepository.findAll());
        } catch (Exception ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_EXCEPTION);
        }
    }

    @Override
    public InsurancePolicyDto saveInsurancePolicy(InsurancePolicyDto insurancePolicyDto) {
        Optional<Client> clientDtoOptional = null;
        try {
            if (insurancePolicyDto == null) {
                throw new CarProtectionException.EntityIsEmptyException();
            }
            if (insurancePolicyDto.getClientId() != null) {
                clientDtoOptional = clientRepository.findById(insurancePolicyDto.getClientId());
                if (clientDtoOptional.isPresent() == false) {
                    throw new CarProtectionException.EntityNotFoundException();
                }
            }

            InsurancePolicy insurancePolicy = new InsurancePolicy()
                    .setNumber(insurancePolicyDto.getNumber())
                    .setPlate(insurancePolicyDto.getPlate())
                    .setBegin(insurancePolicyDto.getBegin())
                    .setEnd(insurancePolicyDto.getEnd())
                    .setAmount(insurancePolicyDto.getAmount())
                    .setClient(clientDtoOptional.get());

            return InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyRepository.save(insurancePolicy));
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(CLIENT, ENTITY_NOT_FOUND, insurancePolicyDto.getClientId());
        } catch (Exception ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_EXCEPTION);
        }
    }

    @Override
    public InsurancePolicyDto getInsurancePolicy(String id) {
        try {
            Optional<InsurancePolicy> insurancePolicyOptional = insurancePolicyRepository.findById(id);
            if (insurancePolicyOptional.isPresent() == false) {
                throw new CarProtectionException.EntityNotFoundException();
            }
            return InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyOptional.get());
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_NOT_FOUND, id);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_EXCEPTION);
        }
    }

    @Override
    public InsurancePolicyDto deleteInsurancePolicy(String id) {
        try {
            Optional<InsurancePolicy> insurancePolicyOptional = insurancePolicyRepository.findById(id);
            if (insurancePolicyOptional.isPresent() == false) {
                throw new CarProtectionException.EntityNotFoundException();
            }
            insurancePolicyRepository.delete(insurancePolicyOptional.get());
            return InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyOptional.get());
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_NOT_FOUND, id);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_EXCEPTION);
        }
    }

    @Override
    public InsurancePolicyDto updateInsurancePolicy(String id, InsurancePolicyDto insurancePolicyDto) {
        try {
            Optional<InsurancePolicy> existingInsurancePolicy = insurancePolicyRepository.findById(id);
            if (existingInsurancePolicy.isPresent() == false) {
                throw new CarProtectionException.EntityNotFoundException();
            }

            InsurancePolicy changedInsurancePolicy = new InsurancePolicy()
                    .setNumber(insurancePolicyDto.getNumber())
                    .setPlate(insurancePolicyDto.getPlate())
                    .setBegin(insurancePolicyDto.getBegin())
                    .setEnd(insurancePolicyDto.getEnd())
                    .setAmount(insurancePolicyDto.getAmount())
                    .setClient(existingInsurancePolicy.get().getClient());

            // Merge both objects
            changedInsurancePolicy = ObjectUtil.merge(existingInsurancePolicy.get(), changedInsurancePolicy);
            changedInsurancePolicy = insurancePolicyRepository.save(changedInsurancePolicy);
            return InsurancePolicyMapper.toInsurancePolicyDto(changedInsurancePolicy);
        } catch (CarProtectionException.EntityNotFoundException ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_NOT_FOUND, id);
        } catch (Exception ex) {
            throw CarProtectionException.throwException(INSURANCE_POLICY, ENTITY_EXCEPTION);
        }
    }

}
