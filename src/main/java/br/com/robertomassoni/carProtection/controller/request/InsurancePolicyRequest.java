package br.com.robertomassoni.carProtection.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
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
    private String number;
    private Date begin;
    private Date end;
    private String plate;
    private Double amount;
    private String clientId;
}
