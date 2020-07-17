package com.zalupa.demo.controllers.impl;

import com.zalupa.demo.controllers.ClientControllerInterface;
import com.zalupa.demo.controllers.TracklistControllerInterface;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

@Component
public class ClientController implements ClientControllerInterface {
    @Autowired
    private ClientServiceInterface service;
    @Autowired
    private TracklistControllerInterface tracklistController;

    public ClientDTO validate(String login, String password) {
        return service.getByLoginAndPassword(login, password);
    }

    public String getUserName() {
        return service.getUserInSessionName();
    }

    public int getUserId() {
        return service.getUserInSessionId();
    }

    public List<TracklistDTO> getUserLists() {
        service.setUserLists(tracklistController.getUserLists(service.getUserInSessionId()));
        return service.getLists();
    }

    public void representUserInXML() throws JAXBException, FileNotFoundException {
        service.writeXML();
    }

    public ClientDTO getUser() {
        return service.getUser();
    }
}
