package br.com.robertomassoni.carProtection.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "insurancePolicy")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePolicy {    
    private String id;
    private String number;
    private Date begin;
    private Date end;
    private String plate;
    private Double amount;
    @DBRef
    private Client client;
}
