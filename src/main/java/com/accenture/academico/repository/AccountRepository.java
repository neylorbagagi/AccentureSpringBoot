package com.accenture.academico.repository;

import com.accenture.academico.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Integer> {}
