package com.zalupa.demo.service;

import com.zalupa.demo.controllers.TrackController;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.repo.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class TrackService {

    @Autowired
    private TrackRepo repo;

    @Autowired
    private TrackController trackController;

    @Autowired
    private TrackConverter converter;
    public boolean insert(int tracklistId, String name, String size, String duration) {
        if (!checkOne(name,size,duration)){
            return false;
        }
        else {
            TrackDTO track = new TrackDTO(tracklistId, name, Long.valueOf(size), Long.valueOf(duration));
            repo.save(converter.convertToEntity(track));
            return true;
        }
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
    public boolean checkConstraints(String name, long size, long duration){
        if ((name != "") && ((size > 0) && (size < 10000)) && ((duration > 0) && (duration < 10000))) {
            return true;
        } else return false;
    }
    public TrackDTO showInfo(int trackId) {
        TrackDTO track = converter.convertToDTO(repo.findByTrackId(trackId));
        return track;
    }

    public boolean updateInfo(int trackId, String name, String size, String duration) {
        if (!checkOne(name,size,duration)){
            return false;
        }
        else {
            TrackDTO track = converter.convertToDTO(repo.findByTrackId(trackId));
            track.setDuration(Long.valueOf(duration));
            track.setSize(Long.valueOf(size));
            track.setName(name);
            repo.save(converter.convertToEntity(track));
            return true;
        }
    }

    public void remove(int trackId) {
        TrackDTO track = converter.convertToDTO(repo.findByTrackId(trackId));
        repo.delete(converter.convertToEntity(track));
    }
    public List<TrackDTO> find(int tracklistId){
        List<TrackDTO> tracks = converter.convertListToDTO(repo.findByTracklistId(tracklistId));
        return tracks;
    }
    public boolean checkList(List<TrackDTO> tracks){

        for (TrackDTO track : tracks){
            if (!checkConstraints(track.getName(),track.getSize(),track.getDuration())) {
                return false;
            }
        }
        return true;
    }
    public boolean save(List<TrackDTO> tracks, int id){
        try{
            for (TrackDTO track : tracks){
                track.setTracklistId(id);
                repo.save(converter.convertToEntity(track));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
