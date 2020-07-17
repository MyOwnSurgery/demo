package com.zalupa.demo.converters;

import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.entities.Client;

public interface ClientConverter {
    public ClientDTO convertToDTO(Client client);
    public Client convertToEntity(ClientDTO client);
}
