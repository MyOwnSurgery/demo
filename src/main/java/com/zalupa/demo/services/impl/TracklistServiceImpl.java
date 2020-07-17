package com.zalupa.demo.services.impl;

import com.zalupa.demo.converters.TracklistConverter;
import com.zalupa.demo.converters.impl.TracklistConverterImpl;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.repos.TracklistRepo;
import com.zalupa.demo.services.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TracklistServiceImpl implements TracklistService {
    @Autowired
    private TracklistConverter converter;
    @Autowired
    private TracklistRepo repo;

    public List<TracklistDTO> getLists(int clientID) {
        return converter.convertListToDTO(repo.findByClientId(clientID));
    }


    public boolean addList(TracklistDTO tracklist, ClientDTO client) {
        TracklistDTO tmp = new TracklistDTO();
        try {
            tmp.setClient(client);
            tmp.setId(tracklist.getId());
            repo.save(converter.convertToEntity(tmp));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void deleteList(int id){
        repo.deleteById(id);
    }
}
