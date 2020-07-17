package com.zalupa.demo.controllers;

import com.zalupa.demo.dto.TrackDTO;

import java.util.List;

public interface TrackControllerInterface {
    public boolean addTrack(int tracklistId, String name, String size, String duration);
    public TrackDTO getPlaceholders(int trackId);
    public boolean updateTrack(int trackId, String name, String size, String duration);
    public void deleteTrack(int trackId);
    public List<TrackDTO> findTracksByTracklistId(int tracklistId);
    public boolean checkTracks(List<TrackDTO> tracks);
    public boolean saveTracks(List<TrackDTO> tracks, int id);
}
