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
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String token = null;
    static String newClientId = null;

    @Test
    public void testCaddClient() throws Exception {
        URI uri = new URI("/api/client");
        String json = "{\"name\": \"Roberto\", \"cpf\": \"880.286.060-24\", \"city\": \"Rio Preto\", \"state\": \"SP\"}";
        
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
    public void testDeditClient() throws Exception {
        URI uri = new URI("/api/client/" + ClientControllerTest.newClientId);
        String json = "{\"name\": \"Roberto\", \"cpf\": \"12345678909\", \"city\": \"São Paulo\", \"state\": \"SP\"}";
        
        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testEgetAllClients() throws Exception {
        URI uri = new URI("/api/client");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testFgetOneClient() throws Exception {
        URI uri = new URI("/api/client/" + ClientControllerTest.newClientId);

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testGdeleteClient() throws Exception {
        URI uri = new URI("/api/client/" + ClientControllerTest.newClientId);

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    
    @Test
    public void testHdeleteNonExistentClient() throws Exception {
        URI uri = new URI("/api/client/9999");

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}
