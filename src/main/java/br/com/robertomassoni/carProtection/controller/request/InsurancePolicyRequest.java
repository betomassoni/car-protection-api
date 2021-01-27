package br.com.robertomassoni.carProtection.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsurancePolicyRequest {
    
    @NotBlank(message = "Number is required.")
    private String number;
    
    @NotBlank(message = "Begin Date is required.")  
    private Date begin;
    
    @NotBlank(message = "End Date is required.")
    private Date end;
    
    @NotBlank(message = "Plate is required.")
    private String plate;
    
    @NotBlank(message = "Amount is required.")
    private Double amount;
    
    @NotBlank(message = "Client id is required.")
    private String clientId;
}
