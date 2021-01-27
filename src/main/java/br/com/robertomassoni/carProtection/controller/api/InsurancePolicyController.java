package br.com.robertomassoni.carProtection.controller.api;

import br.com.robertomassoni.carProtection.dto.mapper.InsurancePolicyMapper;
import br.com.robertomassoni.carProtection.dto.response.Response;
import br.com.robertomassoni.carProtection.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.robertomassoni.carProtection.controller.request.InsurancePolicyRequest;
        
@RestController
@RequestMapping("/api/insurance-policy")
public class InsurancePolicyController {
    
    @Autowired
    private InsurancePolicyService insurancePolicyService;
    
    @GetMapping()
    public Response getAllInsurancePolicy(Pageable pageable) {
        return Response.ok().setContent(insurancePolicyService.getInsurancePolicies());
    } 
    
    @GetMapping("/{id}")
    public Response getInsurancePolicy(@PathVariable("id") String id) {
        return Response.ok().setContent(insurancePolicyService.getInsurancePolicy(id));
    }
    
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") String id) {
        return Response.ok().setContent(insurancePolicyService.deleteInsurancePolicy(id));
    }
    
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") String id, @RequestBody InsurancePolicyRequest insurancePolicyRequest) {
        return Response.ok().setContent(insurancePolicyService.updateInsurancePolicy(id, InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyRequest)));
    }
    
    @PostMapping()
    public Response save(@RequestBody InsurancePolicyRequest insurancePolicyRequest) {
        return Response.ok().setContent(insurancePolicyService.saveInsurancePolicy(InsurancePolicyMapper.toInsurancePolicyDto(insurancePolicyRequest)));
    }
}
