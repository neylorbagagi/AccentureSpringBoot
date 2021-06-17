package com.accenture.academico.controller;

import com.accenture.academico.model.Account;
import com.accenture.academico.model.Client;
import com.accenture.academico.service.ClientService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientController.class)
@WithMockUser
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    Account mockAccount = new Account(){{ setId(1);}};
    Client mockClient = new Client("Gabriela","35906773843","15-981-169-124",mockAccount);
    List<Client> mockClients = new ArrayList<Client>(){{
        add(mockClient);
    }};

    String exampleClientJson = "{\"name\":\"Gabriela\",\"cpf\":\"35906773843\",\"fone\":\"15-981-169-124\"}";

    @Test
    public void getAllClientTest() throws Exception{

        Mockito.when(this.clientService.getAllClients()).thenReturn(mockClients);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/clients")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "[{\"name\":\"Gabriela\",\"cpf\":\"35906773843\",\"fone\":\"15-981-169-124\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getClientTest() throws Exception{

        Mockito.when(this.clientService.getClientById(Mockito.anyInt())).thenReturn(mockClient);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/client/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "{\"name\":\"Gabriela\",\"cpf\":\"35906773843\",\"fone\":\"15-981-169-124\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void saveClientTest() throws Exception{

        Mockito.when(this.clientService.saveOrUpdateClient(Mockito.any(Client.class))).thenReturn(mockClient);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/client")
                .accept(MediaType.APPLICATION_JSON).content(exampleClientJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteClientTest() throws Exception{

        Mockito.when(this.clientService.delete(Mockito.anyInt())).thenReturn(mockClient.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/client/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleClientJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
