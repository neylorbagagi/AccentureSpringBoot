package com.accenture.academico.controller;

import com.accenture.academico.model.Branch;
import com.accenture.academico.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private Branch getBranch(@PathVariable("id") int id) {
        return service.getBranchById(id);
    }

    @DeleteMapping("/branch/{id}")
    private void deleteBranch(@PathVariable("id") int id) {
        service.delete(id);
    }
    
    @PostMapping("/branch")
    public Branch saveBranch(@RequestBody Branch branch){
        return service.saveOrUpdateBranch(branch);
    }
    
}
