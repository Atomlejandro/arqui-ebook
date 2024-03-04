package com.apibooks.apibooks.service;

import com.apibooks.apibooks.entities.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<Client> findAll();
    Optional<Client> findById(Long idClient);

    void save(Client client);

    void deleteById(Long idClient);
}
