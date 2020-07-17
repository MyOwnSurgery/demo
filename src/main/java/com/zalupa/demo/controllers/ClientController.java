package com.zalupa.demo.controllers;

import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TracklistDTO;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface ClientController {
    public ClientDTO validate(String login, String password);
    public String getUserName();
    public int getUserId();
    public List<TracklistDTO> getUserLists();
    public void representUserInXML() throws JAXBException, FileNotFoundException;
    public ClientDTO getUser();
}
