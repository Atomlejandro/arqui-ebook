package com.apibooks.apibooks.persistence.impl;

import com.apibooks.apibooks.entities.Client;
import com.apibooks.apibooks.persistence.IClientDAO;
import com.apibooks.apibooks.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class ClientDAOImpl implements IClientDAO {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteById(Long idClient) {

        clientRepository.deleteById(idClient);

    }
}
