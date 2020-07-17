package com.zalupa.demo.services;

import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TracklistDTO;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface ClientService {
    public ClientDTO getByLoginAndPassword(String login, String password);
}
