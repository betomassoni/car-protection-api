package br.com.robertomassoni.carProtection.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import br.com.robertomassoni.carProtection.dto.model.InsurancePolicyDto;
import br.com.robertomassoni.carProtection.model.InsurancePolicy;
import br.com.robertomassoni.carProtection.controller.request.InsurancePolicyRequest;

public class InsurancePolicyMapper {
        
    public static InsurancePolicyDto toInsurancePolicyDto(InsurancePolicy insurancePolicy) {
        InsurancePolicyDto insurancePolicyDto = null;
        if (insurancePolicy != null) {                        
            insurancePolicyDto = new InsurancePolicyDto()
                    .setId(insurancePolicy.getId())
                    .setNumber(insurancePolicy.getNumber())
                    .setPlate(insurancePolicy.getPlate())
                    .setBegin(insurancePolicy.getBegin())
                    .setEnd(insurancePolicy.getEnd())
                    .setAmount(insurancePolicy.getAmount())            
                    .setClientId(insurancePolicy.getClient().getId());
        }
        return insurancePolicyDto;
    }
    
    public static List<InsurancePolicyDto> toInsurancePolicyDto(List<InsurancePolicy> insurancePolicyList) {
        ArrayList<InsurancePolicyDto> quoteDtoList = new ArrayList();

        insurancePolicyList.stream().forEach((insurancePolicy) -> {
            quoteDtoList.add(toInsurancePolicyDto(insurancePolicy));
        });

        return quoteDtoList;
    }
    
    public static InsurancePolicyDto toInsurancePolicyDto(InsurancePolicyRequest request) {        
        InsurancePolicyDto insurancePolicyDto = null;
        if (request != null) {
            insurancePolicyDto = new InsurancePolicyDto()
                    .setNumber(request.getNumber())
                    .setPlate(request.getPlate())
                    .setBegin(request.getBegin())
                    .setEnd(request.getEnd())
                    .setAmount(request.getAmount())
                    .setClientId(request.getClientId()); 
        }
        return insurancePolicyDto;
    }
    
    
}
