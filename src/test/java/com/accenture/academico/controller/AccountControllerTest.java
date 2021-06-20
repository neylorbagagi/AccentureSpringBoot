package com.accenture.academico.controller;

import com.accenture.academico.model.*;
import com.accenture.academico.model.Account;
import com.accenture.academico.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
@WithMockUser
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account mockAccount;
    private ArrayList<Account> mockAccounts;
    private String exampleAccountJson;
    private String exampleListAccountJson;

    public AccountControllerTest() {

        Account mockClientAccount = new Account(){{ setId(1);}};
        Client mockClient = new Client("Gabriela","35906773843","15-981-169-124",mockClientAccount);

        Branch mockBranch = new Branch("Paulista","Av Paulista 1000","115551020",new ArrayList<>());
        mockBranch.setId(1);
        //this.mockAccount = new Account(){{ setId(1);}};
        this.mockAccount = new Account("0001",0,mockClient,new ArrayList<Statement>(),mockBranch);

        this.mockAccounts = new ArrayList<Account>(){{
            add(mockAccount);
        }};

        this.exampleAccountJson = "{\"number\": \"0001\",\"client\":{\"name\": \"Gabriela\",\"cpf\": \"35906773843\",\"fone\":\"15-981-169-124\"},\"statements\":[]}";
        this.exampleListAccountJson = "["+this.exampleAccountJson+"]";

    }


//
//    Account mockClientAccount = new Account(){{ setId(1);}};
//    Client mockClient = new Client("Gabriela","35906773843","15-981-169-124",mockClientAccount);
//
//    List<Account> accounts = new ArrayList<>();
//    Branch mockBranch = new Branch(){{ setId(1);}}; //new Branch("Paulista","Av Paulista 1000","115551020",accounts);
//
//    List<Statement> statements = new ArrayList<>();
//    Account mockAccount = new Account("0001",0.0,mockClient,statements,mockBranch);
//    List<Account> mockAccounts = new ArrayList<Account>(){{
//        add(mockAccount);
//    }};
//
//
//
//    String exampleAccountJson = "{\"number\": \"0001\",\"client\":{\"name\": \"Gabriela From Account\",\"cpf\": \"35906773843\",\"fone\":\"15-981-169-124\"},\"branch\":{\"id\":\"1\"}}";
//
//    public void setUp(){
//
//        Account mockClientAccount = new Account(){{ setId(1);}};
//        Client mockClient = new Client("Gabriela","35906773843","15-981-169-124",mockClientAccount);
//
//        Branch mockBranch = new Branch("Paulista","Av Paulista 1000","115551020",new ArrayList<>());
//
//        //this.mockAccount = new Account(){{ setId(1);}};
//        this.mockAccount = new Account("0001",0,mockClient,new ArrayList<Statement>(),mockBranch);
//
//        this.mockAccounts = new ArrayList<Account>(){{
//            add(mockAccount);
//        }};
//
//        this.exampleAccountJson = "{\"number\": \"0001\",\"client\":{\"name\": \"Gabriela From Account\",\"cpf\": \"35906773843\",\"fone\":\"15-981-169-124\"},\"branch\":{\"id\":\"1\"}}";
//        this.exampleListAccountJson = "["+this.exampleAccountJson+"]";
//
//    }

    @Test
    public void getAllAccountTest() throws Exception{


        Mockito.when(this.accountService.getAllAccounts()).thenReturn(mockAccounts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/accounts")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        JSONAssert.assertEquals(this.exampleListAccountJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getAccountTest() throws Exception{

        Mockito.when(this.accountService.getAccountById(Mockito.anyInt())).thenReturn(mockAccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/account/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());


        JSONAssert.assertEquals(exampleAccountJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void saveAccountTest() throws Exception{

        Mockito.when(this.accountService.saveOrUpdateAccount(Mockito.any(Account.class))).thenReturn(mockAccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account")
                .accept(MediaType.APPLICATION_JSON).content(exampleAccountJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteAccountTest() throws Exception{

        Mockito.when(this.accountService.delete(Mockito.anyInt())).thenReturn(mockAccount.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/account/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleAccountJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
