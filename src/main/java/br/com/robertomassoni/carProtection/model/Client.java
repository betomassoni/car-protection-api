package br.com.robertomassoni.carProtection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "clients")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client {    
    private String id;
    private String name;
    private String cpf;
    private String city;
    private String state;
}
