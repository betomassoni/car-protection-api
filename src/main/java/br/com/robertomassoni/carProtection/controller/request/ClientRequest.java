package br.com.robertomassoni.carProtection.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequest {
     
    @NotBlank(message = "Name is required.")
    private String name;
    
    @NotBlank(message = "CPF is required.")
    private String cpf;
    
    @NotBlank(message = "City is required.")
    private String city;
    
    @NotBlank(message = "State is required.")
    private String state;
}
