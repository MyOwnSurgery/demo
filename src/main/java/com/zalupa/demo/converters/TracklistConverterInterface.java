package com.zalupa.demo.converters;

import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.entities.Tracklist;

import java.util.List;

public interface TracklistConverterInterface {
    public Tracklist convertToEntity (TracklistDTO dto);
    public TracklistDTO convertToDTO (Tracklist entity);
    public List<Tracklist> convertListToEntity(List<TracklistDTO> dtoList);
    public List<TracklistDTO> convertListToDTO(List<Tracklist> entityList);
}
