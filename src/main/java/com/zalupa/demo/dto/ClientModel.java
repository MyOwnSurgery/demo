package com.zalupa.demo.dto;

import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.entities.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientModel {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private TracklistRepo tracklistRepo;
    @Autowired
    private TrackRepo trackRepo;
    public Client client;
    public Tracklist tracklist;
    public List<Tracklist> tracklists;
    public List<Track> tracks;

    public Client validate(String login, String password) {
        client = clientRepo.findByLoginAndPassword(login, password);
        return client;
    }

    public String getUserName() {
        return client.getName();
    }

    public List<Tracklist> getUserLists() {
        int id = client.getID();
        tracklists = tracklistRepo.findByClientId(id);

        for (int i = 0; i < tracklists.size(); i++) {
            int a = tracklists.get(i).getId();
            tracks = trackRepo.findByTracklistId(a);
            for (int j = 0; j < tracks.size(); j++) {
                tracklists.get(i).addTrack(tracks.get(j));
            }

        }
        return tracklists;
    }
    public void addTrack(int tracklistId, String name, long size, long duration){
        int id = trackRepo.findMaxTrackId() + 1;
        Track track = new Track(id,tracklistId,name,size,duration);
        trackRepo.save(track);
    }
    public Track showTrackInfo(int trackId){
        Track track = trackRepo.findByTrackId(trackId);
        return track;
    }
    public void updateTrackInfo(int trackId, String name, long size, long duration){
        Track track = trackRepo.findByTrackId(trackId);
        track.setDuration(duration);
        track.setSize(size);
        track.setName(name);
        trackRepo.save(track);
    }
    public void deleteTrack(int trackId){
        Track track = trackRepo.findByTrackId(trackId);
        trackRepo.delete(track);
    }

}

