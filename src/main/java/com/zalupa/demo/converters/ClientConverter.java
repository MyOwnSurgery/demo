package com.zalupa.demo.converters;
import org.modelmapper.ModelMapper;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter{

    public ClientDTO convertToDTO(Client client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, ClientDTO.class);
    }
    public Client convertToEntity(ClientDTO client) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(client, Client.class);
    }

}

