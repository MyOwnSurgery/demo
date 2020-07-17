package com.zalupa.demo.controllers.impl;

import com.zalupa.demo.controllers.ClientController;
import com.zalupa.demo.controllers.TracklistController;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class ClientControllerImpl implements ClientController {
    @Autowired
    private ClientService service;
    @Autowired
    private TracklistController tracklistController;
    @Autowired
    private HttpSession session;

    public ClientDTO getUser() {
        if (session != null) {
            return (ClientDTO) session.getAttribute("user");
        } else return null;
    }

    public void setUser(ClientDTO user) {
        session.setAttribute("user", user);
    }

    public List<TracklistDTO> getUserLists(){
        ClientDTO client = setUserLists();
        return client.getTracklists();
    }

    public ClientDTO validate(String login, String password) {
        ClientDTO client = service.getByLoginAndPassword(login,password);
        setUser(client);
        return client;
    }

    public String getUserName() {
        ClientDTO client = getUser();
        return client.getName();
    }
    public int getUserId() {
        ClientDTO client = getUser();
        return client.getID();
    }
    public ClientDTO setUserLists(){
        ClientDTO client = getUser();
        client.setTracklists(tracklistController.getUserLists(getUserId()));
        setUser(client);
        return client;
    }
    public void representUserInXML() throws JAXBException, FileNotFoundException {
        ClientDTO clientDTO = getUser();
        FileOutputStream stream = new FileOutputStream("src/main/resources/templates/outputFile.xml");
        JAXBContext context = JAXBContext.newInstance(ClientDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(clientDTO, stream);
    }
}
