package com.apibooks.apibooks.repository;

import com.apibooks.apibooks.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {



}
