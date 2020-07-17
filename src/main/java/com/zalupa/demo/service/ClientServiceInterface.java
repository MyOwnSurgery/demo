package com.zalupa.demo.service;

import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TracklistDTO;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface ClientServiceInterface {
    public ClientDTO getUser();
    public void setUser(ClientDTO user);
    public List<TracklistDTO> getLists();
    public ClientDTO getByLoginAndPassword(String login, String password);
    public String getUserInSessionName();
    public int getUserInSessionId();
    public void setUserLists(List<TracklistDTO> tracklists);
    public void writeXML() throws JAXBException, FileNotFoundException;

}
