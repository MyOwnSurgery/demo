package com.zalupa.demo.controllers.impl;

import com.zalupa.demo.controllers.ClientController;
import com.zalupa.demo.controllers.TrackController;
import com.zalupa.demo.controllers.TracklistController;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.services.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class TracklistControllerImpl implements TracklistController {
    @Autowired
    private ClientController clientController;
    @Autowired
    private TracklistService service;
    @Autowired
    private TrackController trackController;

    public List<TracklistDTO> getUserLists(int clientId) {
        return fillLists(service.getLists(clientId));
    }

    public List<TracklistDTO> fillLists(List<TracklistDTO> tracklists) {
        for (TracklistDTO tracklist : tracklists) {
            tracklist.setTracks(trackController.findTracksByTracklistId(tracklist.getId()));
        }
        return tracklists;
    }


    public boolean addTracklistFromXML(MultipartFile file) {
        TracklistDTO tracklist = new TracklistDTO();
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("-uploaded")));
                stream.write(bytes);
                stream.close();
            } else {
                return false;
            }
            File prFile = new File("-uploaded");
            JAXBContext context = JAXBContext.newInstance(TracklistDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tracklist = (TracklistDTO) unmarshaller.unmarshal((File) prFile);
        } catch (Exception e) {
            return false;
        }
        if (checkTracklist(tracklist)) {
            if (!service.addList(tracklist, clientController.getUser())) {
                return false;
            }
            if (!trackController.saveTracks(tracklist.getTracks(), tracklist.getId())) {
                service.deleteList(tracklist.getId());
                return false;
            }
                return true;
        } else
            return false;
    }

    public boolean checkTracklist(TracklistDTO tracklistDTO) {
        return checkTracklistItself(tracklistDTO) && trackController.checkTracks(tracklistDTO.getTracks());
    }

    public boolean checkTracklistItself(TracklistDTO tracklist) {
        if (tracklist != null) {
            return true;
        } else return false;

    }

}
