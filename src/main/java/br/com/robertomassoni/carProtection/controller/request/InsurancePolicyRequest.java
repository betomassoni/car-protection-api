package br.com.robertomassoni.carProtection.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Begin Date is required.")
    private Date begin;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "End Date is required.")
    private Date end;
    
    @NotBlank(message = "Plate is required.")
    private String plate;
    
    @NotNull(message = "Amount is required.")
    private Double amount;
    
    @NotBlank(message = "Client id is required.")
    private String clientId;
        
}
