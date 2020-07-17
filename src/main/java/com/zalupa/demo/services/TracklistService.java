package com.zalupa.demo.services;

import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.dto.TracklistDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TracklistService {
    public List<TracklistDTO> getLists(int clientID);
    public boolean addList(TracklistDTO tracklist, ClientDTO client);
    public void deleteList(int id);
}
