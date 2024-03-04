package com.apibooks.apibooks.service.impl;

import com.apibooks.apibooks.entities.Client;
import com.apibooks.apibooks.persistence.IClientDAO;
import com.apibooks.apibooks.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private IClientDAO clientDAO;
    @Override
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    @Override
    public Optional<Client> findById(Long idClient) {
        return clientDAO.findById(idClient);
    }

    @Override
    public void save(Client client) {
    clientDAO.save(client);
    }

    @Override
    public void deleteById(Long idClient) {
    clientDAO.deleteById(idClient);
    }
}
