package com.zalupa.demo.converters;
import org.modelmapper.ModelMapper;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter{
    @Autowired
private ModelMapper modelMapper;
    public ClientDTO convertToDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
    public Client convertToEntity(ClientDTO client) {
        return modelMapper.map(client, Client.class);
    }

}

