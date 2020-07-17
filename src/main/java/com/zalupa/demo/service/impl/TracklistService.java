package com.zalupa.demo.service.impl;

import com.zalupa.demo.converters.impl.TracklistConverter;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.repo.TracklistRepo;
import com.zalupa.demo.service.TracklistServiceInterface;
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
public class TracklistService implements TracklistServiceInterface {
    @Autowired
    private TracklistConverter converter;
    @Autowired
    private TracklistRepo repo;

    public List<TracklistDTO> getLists(int clientID) {
        return converter.convertListToDTO(repo.findByClientId(clientID));
    }

    public TracklistDTO readXML(MultipartFile file) {
        TracklistDTO tracklist;
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("-uploaded")));
                stream.write(bytes);
                stream.close();
            } else {
                return null;
            }
            File prFile = new File("-uploaded");
            JAXBContext context = JAXBContext.newInstance(TracklistDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tracklist = (TracklistDTO) unmarshaller.unmarshal((File) prFile);
        } catch (Exception e) {
            return null;
        }
        return tracklist;
    }

    public boolean checkTracklistItself(TracklistDTO tracklist) {

        if (tracklist != null) {
            return true;
        } else return false;

    }

    public boolean save(TracklistDTO tracklist, ClientDTO client) {
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

    public TracklistDTO setTracks(TracklistDTO tracklist, List<TrackDTO> tracks) {
        tracklist.setTracks(tracks);
        return tracklist;
    }
    public void delete(int id){
        repo.deleteById(id);
    }
}
