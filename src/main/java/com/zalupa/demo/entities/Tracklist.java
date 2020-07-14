package com.zalupa.demo.entities;

import javax.persistence.*;

import javax.xml.bind.annotation.*;
import java.io.*;

import java.util.*;
@Entity
@Table(name = "tracklist")

@XmlRootElement(name = "tracklist")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tracklist implements Serializable {
    @OneToMany
    @XmlElementWrapper(name = "tracks")
    @XmlElement(name = "track")
    private List<Track> tracks;
    @ManyToOne
    @JoinColumn(name = "TRACKLIST_CLIENT_id")
    private Client client;
    @Id
    @Column(name = "TRACKLIST_TRACKLIST_id")
    private int id;


    public Client getClient() {
        return client;
    }
    @XmlElementWrapper(name = "tracks")
    @XmlElement(name = "track")

    public void setClient(Client client) {
        this.client = client;
    }
    public Tracklist(){

    }
  /*  public Tracklist(int id) {
        this.id = id;
        tracks = new ArrayList<Track>();
    }

    public Tracklist(int id, List<Track> tracks) {
        this.id = id;
        this.tracks = tracks;
    }

*/
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }



    public List<Track> getTracks() {
        return tracks;
    }
    public Track getTrack (int index) {
        return tracks.get(index);
    }

    public int length() {
        return tracks.size();
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void deleteTrack(int index) {
        if (index < tracks.size()) {
            tracks.remove(index);
        } else {
            System.out.println("Некорректная попытка удаления");
            System.exit(0);
        }


    }
    public void setTracks(List<Track> tracks){
        this.tracks = tracks;
    }









   /* public String toString() {
        return "Tracklist{"
                + "id=" + id
                + ", tracks=" + tracks.toString()
                + '}';
    }
*/
}
