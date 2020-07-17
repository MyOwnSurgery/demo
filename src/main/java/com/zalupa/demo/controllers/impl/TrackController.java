package com.zalupa.demo.controllers.impl;


import com.zalupa.demo.controllers.TrackControllerInterface;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.service.TrackServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackController implements TrackControllerInterface {
    @Autowired
    private TrackServiceInterface service;

    public boolean addTrack(int tracklistId, String name, String size, String duration) {
        return service.insert(tracklistId, name, size, duration);
    }

    public TrackDTO getPlaceholders(int trackId) {
        return service.showInfo(trackId);
    }

    public boolean updateTrack(int trackId, String name, String size, String duration) {
        return service.updateInfo(trackId, name, size, duration);
    }

    public void deleteTrack(int trackId) {
        service.remove(trackId);
    }

    public List<TrackDTO> findTracksByTracklistId(int tracklistId) {
        return service.find(tracklistId);
    }

    public boolean checkTracks(List<TrackDTO> tracks) {

        return service.checkList(tracks);
    }

    public boolean saveTracks(List<TrackDTO> tracks, int id) {
        return service.save(tracks, id);
    }

}
