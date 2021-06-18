package com.accenture.academico.service;

import com.accenture.academico.model.Branch;
import com.accenture.academico.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchService {

    @Autowired
    BranchRepository repository;

    public List<Branch> getAllBranchs(){
        List<Branch> branchList = new ArrayList<Branch>();
        repository.findAll().forEach(branch -> branchList.add(branch));
        return branchList;
    }

    public Branch getBranchById(int id) {
        return repository.findById(id).get();
    }

    public Branch saveOrUpdateBranch(Branch branch){
        return repository.save(branch);
    }

    public int delete(int id) {
        repository.deleteById(id);
        return id;
    }
    
}
