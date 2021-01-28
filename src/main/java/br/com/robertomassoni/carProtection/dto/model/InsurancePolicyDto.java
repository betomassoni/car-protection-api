package br.com.robertomassoni.carProtection.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsurancePolicyDto {

    private String id;
    private String number;
    private String begin;
    private String end;
    private String plate; 
    private Double amount; 
    private String clientId;
    private boolean expired;
    private int remaningDays;
        
}
