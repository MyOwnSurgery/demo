package com.zalupa.demo.services;

import com.zalupa.demo.dto.TrackDTO;

import java.util.List;

public interface TrackService {
    public boolean insert(int tracklistId, String name, String size, String duration);
    public TrackDTO getInfo(int trackId);
    public boolean updateInfo(int trackId, String name, String size, String duration);
    public void removeTrack(int trackId);
    public List<TrackDTO> findTracks(int tracklistId);
    public boolean addTracks(List<TrackDTO> tracks, int id);
}
