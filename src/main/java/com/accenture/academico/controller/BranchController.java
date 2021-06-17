package com.accenture.academico.controller;

import com.accenture.academico.exception.RegisterNotFoundException;
import com.accenture.academico.model.Branch;
import com.accenture.academico.model.Branch;
import com.accenture.academico.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class BranchController {

    @Autowired
    BranchService service;

    @GetMapping("/branchs")
    public List<Branch> getAllBranch(){
        return service.getAllBranchs();
    }

    @GetMapping("/branch/{id}")
    private ResponseEntity<Branch> getBranch(@PathVariable("id") int id) throws RegisterNotFoundException {
        Branch branch;
        try{
            branch = service.getBranchById(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }

        return new ResponseEntity<Branch>(branch, HttpStatus.OK);
    }

    @DeleteMapping("/branch/{id}")
    private void deleteBranch(@PathVariable("id") int id) throws RegisterNotFoundException {
        try{
            service.delete(id);
        } catch(Exception exception){
            throw new RegisterNotFoundException();
        }
    }
    
    @PostMapping("/branch")
    public Branch saveBranch(@RequestBody Branch branch, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_CREATED);
        return service.saveOrUpdateBranch(branch);
    }
    
}
