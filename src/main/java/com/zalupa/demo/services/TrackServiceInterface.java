package com.zalupa.demo.services;

import com.zalupa.demo.dto.TrackDTO;

import java.util.List;

public interface TrackServiceInterface {
    public boolean insert(int tracklistId, String name, String size, String duration);
    public boolean checkOne(String name, String size, String duration);
    public boolean checkConstraints(String name, long size, long duration);
    public TrackDTO showInfo(int trackId);
    public boolean updateInfo(int trackId, String name, String size, String duration);
    public void remove(int trackId);
    public List<TrackDTO> find(int tracklistId);
    public boolean checkList(List<TrackDTO> tracks);
    public boolean save(List<TrackDTO> tracks, int id);
}
