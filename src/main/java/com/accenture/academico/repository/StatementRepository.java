package com.accenture.academico.repository;

import com.accenture.academico.model.Statement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends CrudRepository<Statement,Integer> {}
