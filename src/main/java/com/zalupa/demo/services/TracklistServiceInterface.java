package com.zalupa.demo.services;

import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.dto.TracklistDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TracklistServiceInterface {
    public List<TracklistDTO> getLists(int clientID);
    public TracklistDTO readXML(MultipartFile file);
    public boolean checkTracklistItself(TracklistDTO tracklist);
    public boolean save(TracklistDTO tracklist, ClientDTO client);
    public TracklistDTO setTracks(TracklistDTO tracklist, List<TrackDTO> tracks);
    public void delete(int id);
}
