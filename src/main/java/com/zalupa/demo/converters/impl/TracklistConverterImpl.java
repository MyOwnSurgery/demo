package com.zalupa.demo.converters.impl;
import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.converters.TracklistConverter;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.entities.Tracklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class TracklistConverterImpl implements TracklistConverter {
    @Autowired
    public ClientConverter clientConverter;
    @Autowired
    public TrackConverter trackConverter;

    public Tracklist convertToEntity (TracklistDTO dto){
        Tracklist tracklist = new Tracklist();
        tracklist.setId(dto.getId());
        tracklist.setClient(clientConverter.convertToEntity(dto.getClient()));
        List<Track> tracks = trackConverter.convertListToEntity(dto.getTracks());
        tracklist.setTracks(tracks);
        return tracklist;
    }
    public TracklistDTO convertToDTO (Tracklist entity){
        TracklistDTO tracklist = new TracklistDTO();
        tracklist.setId(entity.getId());
        tracklist.setClient(clientConverter.convertToDTO(entity.getClient()));
        List<TrackDTO> tracks = trackConverter.convertListToDTO(entity.getTracks());
        tracklist.setTracks(tracks);
        return tracklist;
    }
    public List<Tracklist> convertListToEntity( List<TracklistDTO> dtoList){
        List<Tracklist> entityList = new ArrayList<>();
        for (TracklistDTO dto:dtoList){
            Tracklist entity = convertToEntity(dto);
            entityList.add(entity);
        }
        return entityList;
    }
    public List<TracklistDTO> convertListToDTO(List<Tracklist> entityList){
        List<TracklistDTO> dtoList = new ArrayList<>();
        for (Tracklist entity: entityList){
            TracklistDTO dto = convertToDTO(entity);
            dtoList.add(dto);
        }
        return dtoList;

    }
}
