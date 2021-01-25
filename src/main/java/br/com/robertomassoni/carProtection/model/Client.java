package br.com.robertomassoni.carProtection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "clients")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {    
    private String name;
}
