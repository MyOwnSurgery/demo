package com.zalupa.demo.converters.impl;

import com.zalupa.demo.converters.TrackConverterInterface;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.entities.Track;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TrackConverter implements TrackConverterInterface {

    public TrackDTO convertToDTO(Track track) {
        TrackDTO dto = new TrackDTO();
        dto.setTracklistId(track.getTracklistId());
        dto.setId(track.getTrackId());
        dto.setDuration(track.getDuration());
        dto.setName(track.getName());
        dto.setSize(track.getSize());
        return dto;
    }
    public Track convertToEntity(TrackDTO track) {
        Track entity = new Track();
        entity.setTracklistId(track.getTracklistId());
        entity.setTrackId(track.getId());
        entity.setDuration(track.getDuration());
        entity.setName(track.getName());
        entity.setSize(track.getSize());
        return entity;
    }


    public List<Track> convertListToEntity(List<TrackDTO> dtoList){
        List<Track> entityList = new ArrayList<Track>();
        if (dtoList!=null) {
            for (TrackDTO dto : dtoList) {
                Track entity = convertToEntity(dto);
                entityList.add(entity);
            }
        }
        return entityList;
    }
    public List<TrackDTO> convertListToDTO(List<Track> entityList){
        List<TrackDTO> dtoList = new ArrayList<TrackDTO>();
        for (Track entity: entityList){
            TrackDTO dto = convertToDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;

    }
}
