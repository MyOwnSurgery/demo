package com.zalupa.demo.service;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.converters.TracklistConverter;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@Component
public class ClientService {
    @Autowired
    private HttpSession session;

    @Autowired
    private ClientRepo repo;

    @Autowired
    private ClientConverter converter;


    public ClientDTO getUser() {
        if (session != null) {
            return (ClientDTO) session.getAttribute("user");
        } else return null;
    }

    public void setUser(ClientDTO user) {

        session.setAttribute("user", user);
    }
    public List<TracklistDTO> getLists(){
        ClientDTO client = (ClientDTO) session.getAttribute("user");
        return client.getTracklists();
    }

    public ClientDTO getByLoginAndPassword(String login, String password) {
        Client client = repo.findByLoginAndPassword(login, password);
        if (client != null) {
            ClientDTO clientDTO = converter.convertToDTO(client);
            setUser(clientDTO);
            return clientDTO;
        }
        else return null;
    }

    public String getUserInSessionName() {
        ClientDTO clientDTO = getUser();
        return clientDTO.getName();
    }
    public int getUserInSessionId() {
        ClientDTO clientDTO = getUser();
        return clientDTO.getID();
    }
    public void setUserLists(List<TracklistDTO> tracklists){
        ClientDTO clientDTO = getUser();
        clientDTO.setTracklists(tracklists);
        setUser(clientDTO);
    }

    public void writeXML() throws JAXBException, FileNotFoundException {
        ClientDTO clientDTO = getUser();
        FileOutputStream stream = new FileOutputStream("src/main/resources/templates/outputFile.xml");
        JAXBContext context = JAXBContext.newInstance(ClientDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(clientDTO, stream);
    }

}