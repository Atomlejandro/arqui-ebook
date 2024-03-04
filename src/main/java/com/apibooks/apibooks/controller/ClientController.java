package com.apibooks.apibooks.controller;

import com.apibooks.apibooks.controller.dto.ClientDTO;
import com.apibooks.apibooks.entities.Client;
import com.apibooks.apibooks.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {


    @Autowired
    private IClientService clientService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Client> clientOptional = clientService.findById(id);


        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            ClientDTO clientDTO = ClientDTO.builder().idClient(client.getIdClient())
                    .name(client.getName())
                    .job(client.getJob())
                    .sex(client.getSex())
                    .city(client.getCity())
                    .country(client.getCountry())
                    .card(client.getCard())
                    .purchaseOrderList(client.getPurchaseOrderList())
                    .build();
            return ResponseEntity.ok(clientDTO);

        }
        return ResponseEntity.notFound().build();
    }
@GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
    List<ClientDTO> clientList = clientService.findAll()
            .stream()
            .map(client -> ClientDTO.builder()
                    .idClient(client.getIdClient())
                    .name(client.getName())
                    .job(client.getJob())
                    .sex(client.getSex())
                    .city(client.getCity())
                    .country(client.getCountry())
                    .card(client.getCard())
                    .purchaseOrderList(client.getPurchaseOrderList())
                    .build()).toList();

    return ResponseEntity.ok(clientList);
    }
}
