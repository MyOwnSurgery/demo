package com.zalupa.demo.models;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Алексей
 */


@Entity
@Table(name = "CLIENT")

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {

    @Id
    @Column(name = "CLIENT_ID")
    private int id;
    @Column(name = "CLIENT_LOGIN")
    private String login;
    @Column(name = "CLIENT_NAME")
    private String name;
    @XmlTransient
    @Column(name = "CLIENT_PASSWORD")
    private String password;

  //  @XmlElementWrapper(name = "tracklists")
   // @XmlElement(name = "tracklist")
  //  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    //private List<Tracklist> tracklists;

    public Client(int id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
    //   tracklists = new ArrayList<Tracklist>();
    }

    public Client(String login,String password){
        this.login = login;
        this.password = password;
      //  tracklists = new ArrayList<Tracklist>();
    }
    public Client() {

    }

  //  public void setTracklists(List<Tracklist> tracklists) {
    //    this.tracklists = tracklists;
 //   }
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
/*
    public int GetSize() {
        return tracklists.size();
    }

    public void AddList(Tracklist tracks) {
        tracklists.add(tracks);
    }

    public Tracklist GetList(int n) {
        return tracklists.get(n);
    }


    public List<Tracklist> GetLists(){
        return this.tracklists;
    }

    public void RemoveList(int n) {
        tracklists.remove(n);
    }

    public int getId() {
        return id;
    }*/
}
