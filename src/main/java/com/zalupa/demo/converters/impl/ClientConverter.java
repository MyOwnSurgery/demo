package com.zalupa.demo.converters.impl;
import com.zalupa.demo.converters.ClientConverterInterface;
import org.modelmapper.ModelMapper;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter implements ClientConverterInterface {
    @Autowired
private ModelMapper modelMapper;
    public ClientDTO convertToDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
    public Client convertToEntity(ClientDTO client) {
        return modelMapper.map(client, Client.class);
    }

}

