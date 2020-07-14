package com.zalupa.demo.dto;

import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Track;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "tracklist")
@XmlAccessorType(XmlAccessType.FIELD)
public class TracklistDTO {
    private int id;
    @XmlElementWrapper(name = "tracks")
    @XmlElement(name = "track")
    private List<TrackDTO> tracks;
    @XmlTransient
    private ClientDTO client;
    private int clientId;
    public ClientDTO getClient() {
        return client;
    }
    public void setClient(ClientDTO client) {
        this.client = client;
    }
    public TracklistDTO(){

    }
    public void setId(int id){
        this.id = id;
    }
   /* public void setClientId(int id){
        this.clientId = id;
    }
    public int getClientId(){
        return clientId;
    }
    */

    public void setTracks(List<TrackDTO> tracks){
        this.tracks = tracks;
    }
    public int getId() {
        return id;
    }
    public List<TrackDTO> getTracks() {
        return tracks;
    }
    public TrackDTO getTrack (int index) {
        return tracks.get(index);
    }
    public int length() {
        return tracks.size();
    }
    public void addTrack(TrackDTO track) {
        tracks.add(track);
    }

}
