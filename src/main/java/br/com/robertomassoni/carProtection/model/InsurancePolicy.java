package br.com.robertomassoni.carProtection.model;

import br.com.robertomassoni.carProtection.util.DateUtil;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
    private boolean expired;
    private int remaningDays;
    
    public boolean isExpired() {
        int daysDifference = Math.toIntExact(DateUtil.getDateDiff(DateUtil.today(), this.end, TimeUnit.DAYS));
        return daysDifference < 0;
    }
    
    public int getRemaningDays() {      
        return Math.toIntExact(DateUtil.getDateDiff(DateUtil.today(), this.end, TimeUnit.DAYS)) + 1;
    }
}
