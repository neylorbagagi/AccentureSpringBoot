package com.accenture.academico.controller;

import com.accenture.academico.model.*;
import com.accenture.academico.service.StatementService;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StatementController.class)
@WithMockUser
public class StatementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatementService statementService;

    private Date mockStatementDate;
    private Account mockAccount;
    private Statement mockStatement;
    private List<Statement> mockStatements;
    private String mockStatementDateString;
    private String exampleStatementJson;
    private String exampleListStatementJson;


    public StatementControllerTest() throws ParseException {

        this.mockStatementDate = new Date();

        Account mockClientAccount = new Account(){{ setId(1);}};
        Client mockClient = new Client("Gabriela","35906773843","15-981-169-124",mockClientAccount);

        Branch mockBranch = new Branch("Paulista","Av Paulista 1000","115551020",new ArrayList<>());

        //this.mockAccount = new Account(){{ setId(1);}};
        Account mockAccount = new Account("0001",0,mockClient,new ArrayList<Statement>(),mockBranch);

        this.mockStatement = new Statement(0.0,mockStatementDate,StatementOperation.DEPOSIT,this.mockAccount);

        this.mockStatementDateString = this.mockStatementDate.toString();
        this.mockStatements = new ArrayList<Statement>(){{
            add(mockStatement);
        }};

        this.exampleStatementJson = "{\"id\":0,\"operation\":\"DEPOSIT\"}";
        this.exampleListStatementJson = "["+this.exampleStatementJson+"]";

    }


    @Test
    public void getAllStatementTest() throws Exception{


        Mockito.when(this.statementService.getAllStatements()).thenReturn(mockStatements);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/statements")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(exampleListStatementJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getStatementTest() throws Exception{

        Mockito.when(this.statementService.getStatementById(Mockito.anyInt())).thenReturn(mockStatement);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/statement/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());


        JSONAssert.assertEquals(exampleStatementJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void saveStatementTest() throws Exception{

        Mockito.when(this.statementService.saveOrUpdateStatement(Mockito.any(Statement.class))).thenReturn(mockStatement);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/statement")
                .accept(MediaType.APPLICATION_JSON).content(exampleStatementJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteStatementTest() throws Exception{

        Mockito.when(this.statementService.delete(Mockito.anyInt())).thenReturn(mockStatement.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/statement/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleStatementJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
}
