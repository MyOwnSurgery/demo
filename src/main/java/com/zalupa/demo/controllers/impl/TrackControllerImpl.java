package com.zalupa.demo.controllers.impl;


import com.zalupa.demo.controllers.TrackController;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackControllerImpl implements TrackController {
    @Autowired
    private TrackService service;

    public boolean addTrack(int tracklistId, String name, String size, String duration) {
        if (!checkOne(name,size,duration)){
            return false;
        }
        return service.insert(tracklistId, name, size, duration);
    }

    public TrackDTO getPlaceholders(int trackId) {
        return service.getInfo(trackId);
    }

    public boolean updateTrack(int trackId, String name, String size, String duration) {
        if (!checkOne(name,size,duration)){
            return false;
        }
        return service.updateInfo(trackId, name, size, duration);
    }

    public void deleteTrack(int trackId) {
        service.removeTrack(trackId);
    }

    public List<TrackDTO> findTracksByTracklistId(int tracklistId) {
        return service.findTracks(tracklistId);
    }

    public boolean checkTracks(List<TrackDTO> tracks) {

        for (TrackDTO track : tracks){
            if (!checkConstraints(track.getName(),track.getSize(),track.getDuration())) {
                return false;
            }
        }
        return true;

    }

    public boolean saveTracks(List<TrackDTO> tracks, int id) {
        return service.addTracks(tracks, id);
    }


    public boolean checkConstraints(String name, long size, long duration){
        if ((name != "") && ((size > 0) && (size < 10000)) && ((duration > 0) && (duration < 10000))) {
            return true;
        } else return false;
    }

    public boolean checkOne(String name, String size, String duration) {
        long sizeVal;
        long durVal;
        try {
            sizeVal = Long.valueOf(size);
            durVal = Long.valueOf(duration);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return checkConstraints(name,sizeVal,durVal);
    }
}
