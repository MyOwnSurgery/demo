package com.zalupa.demo.services.impl;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.impl.ClientConverterImpl;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.entities.Client;
import com.zalupa.demo.repos.ClientRepo;
import com.zalupa.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientRepo repo;

    @Autowired
    private ClientConverter converter;


    public ClientDTO getByLoginAndPassword(String login, String password) {
        Client client = repo.findByLoginAndPassword(login, password);
        if (client != null) {
            ClientDTO clientDTO = converter.convertToDTO(client);
            return clientDTO;
        }
        else return null;
    }





}
