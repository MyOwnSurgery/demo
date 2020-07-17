package com.zalupa.demo.services.impl;

import com.zalupa.demo.controllers.impl.TrackControllerImpl;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.converters.impl.TrackConverterImpl;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.repos.TrackRepo;
import com.zalupa.demo.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepo repo;

    @Autowired
    private TrackConverter converter;

    public boolean insert(int tracklistId, String name, String size, String duration) {
        try{
            TrackDTO track = new TrackDTO(tracklistId, name, Long.valueOf(size), Long.valueOf(duration));
            repo.save(converter.convertToEntity(track));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public TrackDTO getInfo(int trackId) {
        TrackDTO track = converter.convertToDTO(repo.findByTrackId(trackId));
        return track;
    }

    public boolean updateInfo(int trackId, String name, String size, String duration) {
        try {
            TrackDTO track = converter.convertToDTO(repo.findByTrackId(trackId));
            track.setDuration(Long.valueOf(duration));
            track.setSize(Long.valueOf(size));
            track.setName(name);
            repo.save(converter.convertToEntity(track));
        }
        catch (Exception e){
            return false;
        }
        return true;

    }

    public void removeTrack(int trackId) {
        TrackDTO track = converter.convertToDTO(repo.findByTrackId(trackId));
        repo.delete(converter.convertToEntity(track));
    }

    public List<TrackDTO> findTracks(int tracklistId){
        List<TrackDTO> tracks = converter.convertListToDTO(repo.findByTracklistId(tracklistId));
        return tracks;
    }

    public boolean addTracks(List<TrackDTO> tracks, int id){
        try{
            for (TrackDTO track : tracks){
                track.setTracklistId(id);
            }
            repo.saveAll(converter.convertListToEntity(tracks));
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
