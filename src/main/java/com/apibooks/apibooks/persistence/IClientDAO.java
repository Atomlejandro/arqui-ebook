package com.apibooks.apibooks.persistence;

import com.apibooks.apibooks.entities.Client;

import java.util.List;
import java.util.Optional;

public interface IClientDAO {
    List<Client> findAll();
    Optional<Client> findById(Long idClient);

    void save(Client client);

    void deleteById(Long idClient);
}
