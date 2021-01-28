package br.com.robertomassoni.carProtection.controller.api;

import java.net.URI;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class InsurancePolicyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String token = null;
    private static String newInsurancePolicyId = null;
    private static String newClientId = null;
    
    @Test
    public void testBaddClient() throws Exception {
        URI uri = new URI("/api/client");
        String json = "{\"name\": \"Isabela\", \"cpf\": \"097.873.730-00\", \"city\": \"Rio Preto\", \"state\": \"SP\"}";
        
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders                
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        String resultString = result.andReturn().getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(resultString);
        JSONObject content = jsonObject.getJSONObject("content");
        ClientControllerTest.newClientId = content.getString("id");        
    }
    
    @Test
    public void testCaddInsurancePolicy() throws Exception {
        URI uri = new URI("/api/insurance-policy");
        String json = "{\"number\": \"23432\", \"begin\": \"2021-01-10\", \"end\": \"2021-01-30\", \"plate\": \"FHY7888\", \"amount\": 123.23, \"clientId\": \""+ ClientControllerTest.newClientId +"\"}";
        
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders                
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
        String resultString = result.andReturn().getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(resultString);
        JSONObject content = jsonObject.getJSONObject("content");
        InsurancePolicyControllerTest.newInsurancePolicyId = content.getString("id");        
    }
    
    @Test
    public void testDeditInsurancePolicy() throws Exception {
        URI uri = new URI("/api/insurance-policy/" + InsurancePolicyControllerTest.newInsurancePolicyId);
        String json = "{\"number\": \"23432\", \"begin\": \"2021-01-10\", \"end\": \"2021-01-30\", \"plate\": \"FHY7888\", \"amount\": 123.23, \"clientId\": \""+ ClientControllerTest.newClientId +"\"}";
        
        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testEgetAllInsurancePolicies() throws Exception {
        URI uri = new URI("/api/insurance-policy");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testFgetOneInsurancePolicy() throws Exception {
        URI uri = new URI("/api/insurance-policy/" + InsurancePolicyControllerTest.newInsurancePolicyId);

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testGdeleteInsurancePolicy() throws Exception {
        URI uri = new URI("/api/insurance-policy/" + InsurancePolicyControllerTest.newInsurancePolicyId);

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testHdeleteNonExistentInsurancePolicy() throws Exception {
        URI uri = new URI("/api/insurance-policy/9999");

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}
