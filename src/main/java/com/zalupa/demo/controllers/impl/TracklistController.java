package com.zalupa.demo.controllers.impl;

import com.zalupa.demo.controllers.ClientControllerInterface;
import com.zalupa.demo.controllers.TrackControllerInterface;
import com.zalupa.demo.controllers.TracklistControllerInterface;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.services.TracklistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class TracklistController implements TracklistControllerInterface {
    @Autowired
    private ClientControllerInterface clientController;
    @Autowired
    private TracklistServiceInterface service;
    @Autowired
    private TrackControllerInterface trackController;

    public List<TracklistDTO> getUserLists(int clientId) {
        return fillLists(service.getLists(clientId));
    }

    public List<TracklistDTO> fillLists(List<TracklistDTO> tracklists) {
        for (TracklistDTO tracklist : tracklists) {
            service.setTracks(tracklist, trackController.findTracksByTracklistId(tracklist.getId()));
        }
        return tracklists;
    }

    public boolean addTracklistFromXML(MultipartFile file) {
        TracklistDTO tracklistDTO = service.readXML(file);
        if (checkTracklist(tracklistDTO)) {
            if (!service.save(tracklistDTO, clientController.getUser())) {
                return false;
            }
            if (!trackController.saveTracks(tracklistDTO.getTracks(), tracklistDTO.getId())) {
                service.delete(tracklistDTO.getId());
                return false;
            }
                return true;
        } else
            return false;
    }

    public boolean checkTracklist(TracklistDTO tracklistDTO) {

        return service.checkTracklistItself(tracklistDTO) && trackController.checkTracks(tracklistDTO.getTracks());
    }


}
