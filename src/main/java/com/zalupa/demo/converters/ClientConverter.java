package com.zalupa.demo.converters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.entities.Client;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
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

