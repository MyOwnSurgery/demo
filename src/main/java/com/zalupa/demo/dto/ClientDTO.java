package com.zalupa.demo.dto;

import javax.xml.bind.annotation.*;
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

