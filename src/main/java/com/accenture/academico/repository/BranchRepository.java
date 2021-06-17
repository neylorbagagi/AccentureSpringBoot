package com.accenture.academico.repository;

import com.accenture.academico.model.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends CrudRepository<Branch,Integer> {}
