package com.zalupa.demo.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * @author Алексей
 */


@Entity
@Table(name = "CLIENT")


public class Client {

    @Id
    @Column(name = "CLIENT_ID")
    private int id;
    @Column(name = "CLIENT_LOGIN")
    private String login;
    @Column(name = "CLIENT_NAME")
    private String name;

    @Column(name = "CLIENT_PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Tracklist> tracklists;

    public Client(int id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Client() {

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

}
