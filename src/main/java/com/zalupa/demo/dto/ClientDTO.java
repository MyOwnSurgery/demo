package com.zalupa.demo.dto;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.converters.TracklistConverter;
import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.entities.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientDTO {

    private int id;
    private String login;
    private String name;
    @XmlTransient
    private String password;
    @XmlElementWrapper(name = "tracklists")
    @XmlElement(name = "tracklist")
    public List<TracklistDTO> tracklists;


    public ClientDTO(int id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;

    }


    public ClientDTO(String login,String password){
        this.login = login;
        this.password = password;

    }
    public ClientDTO() {

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTracklists(List<TracklistDTO> tracklists){
        this.tracklists = tracklists;
    }
    public List<TracklistDTO> getTracklists(){
        return tracklists;
    }
}

