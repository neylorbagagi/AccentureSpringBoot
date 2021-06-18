package com.accenture.academico.controller;

import com.accenture.academico.model.Account;
import com.accenture.academico.model.Branch;
import com.accenture.academico.service.BranchService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BranchController.class)
@WithMockUser
public class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    List<Account> accounts = new ArrayList<>();
    Branch mockBranch = new Branch("Paulista","Av Paulista 1000","115551020",accounts);
    List<Branch> mockBranchs = new ArrayList<Branch>(){{
        add(mockBranch);
    }};


    String exampleBranchJson = "{\"name\":\"Paulista\",\"address\":\"Av Paulista 1000\",\"fone\":\"115551020\"}";

    @Test
    public void getAllBranchTest() throws Exception{

        Mockito.when(this.branchService.getAllBranchs()).thenReturn(mockBranchs);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/branchs")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "[{\"name\":\"Paulista\",\"address\":\"Av Paulista 1000\",\"fone\":\"115551020\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getBranchTest() throws Exception{

        Mockito.when(this.branchService.getBranchById(Mockito.anyInt())).thenReturn(mockBranch);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/branch/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "{\"name\":\"Paulista\",\"address\":\"Av Paulista 1000\",\"fone\":\"115551020\"}";

        JSONAssert.assertEquals(exampleBranchJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void saveBranchTest() throws Exception{

        Mockito.when(this.branchService.saveOrUpdateBranch(Mockito.any(Branch.class))).thenReturn(mockBranch);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/branch")
                .accept(MediaType.APPLICATION_JSON).content(exampleBranchJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteBranchTest() throws Exception{

        Mockito.when(this.branchService.delete(Mockito.anyInt())).thenReturn(mockBranch.getId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/branch/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleBranchJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
