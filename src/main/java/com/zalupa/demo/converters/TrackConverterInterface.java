package com.zalupa.demo.converters;

import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.entities.Track;

import java.util.List;

public interface TrackConverterInterface {
    public TrackDTO convertToDTO(Track track);
    public Track convertToEntity(TrackDTO track);
    public List<Track> convertListToEntity(List<TrackDTO> dtoList);
    public List<TrackDTO> convertListToDTO(List<Track> entityList);
}
