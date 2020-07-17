package com.zalupa.demo.controllers;

import com.zalupa.demo.dto.TracklistDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TracklistControllerInterface {
    public List<TracklistDTO> getUserLists(int clientId);
    public List<TracklistDTO> fillLists(List<TracklistDTO> tracklists);
    public boolean addTracklistFromXML(MultipartFile file);
    public boolean checkTracklist(TracklistDTO tracklistDTO);
}
