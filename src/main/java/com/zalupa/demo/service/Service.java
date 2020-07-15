package com.zalupa.demo.service;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.converters.TracklistConverter;
import com.zalupa.demo.dto.ClientDTO;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.dto.TracklistDTO;
import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@Component
public class Service {
    @Autowired
    private HttpSession session;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private TracklistRepo tracklistRepo;

    @Autowired
    private TrackRepo trackRepo;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private TracklistConverter tracklistConverter;

    @Autowired
    private TrackConverter trackConverter;


    public ClientDTO getUser() {
        if (session != null) {
            return (ClientDTO) session.getAttribute("user");
        } else return null;
    }

    public void setUser(ClientDTO user) {
        session.setAttribute("user", user);
    }

    public ClientDTO validate(String login, String password) {
        Client client = clientRepo.findByLoginAndPassword(login, password);
        if (client != null) {
            ClientDTO clientDTO = clientConverter.convertToDTO(client);
            setUser(clientDTO);
            return clientDTO;
        }
        else return null;
    }

    public String getUserName() {
        ClientDTO clientDTO = getUser();
        return clientDTO.getName();
    }

    public boolean check(String name, String size, String duration) {
        long sizeVal;
        long durVal;
        try {
            sizeVal = Long.valueOf(size);
            durVal = Long.valueOf(duration);
        } catch (IllegalArgumentException e) {
            return false;
        }
        if ((name != "") && ((sizeVal > 0) && (sizeVal < 10000)) && ((durVal > 0) && (durVal < 10000))) {
            return true;
        } else return false;
    }

    public List<TracklistDTO> getUserLists() {
        ClientDTO clientDTO = getUser();
        List<TracklistDTO> tracklists = tracklistConverter.convertListToDTO(tracklistRepo.findByClientId(clientDTO.getID()));
        List<TrackDTO> tracks;
        for (int i = 0; i < tracklists.size(); i++) {
            int a = tracklists.get(i).getId();
            tracks = trackConverter.convertListToDTO(trackRepo.findByTracklistId(a));
            for (int j = 0; j < tracks.size(); j++) {
                tracklists.get(i).addTrack(tracks.get(j));
            }
        }
        clientDTO.setTracklists(tracklists);
        return tracklists;
    }

    public boolean addTrack(int tracklistId, String name, String size, String duration) {
        if (!check(name,size,duration)){
            return false;
        }
        else {
            TrackDTO track = new TrackDTO(tracklistId, name, Long.valueOf(size), Long.valueOf(duration));
            track.setId(trackRepo.findMaxTrackId() + 1);
            trackRepo.save(trackConverter.convertToEntity(track));
            return true;
        }
    }

    public TrackDTO showTrackInfo(int trackId) {
        TrackDTO track = trackConverter.convertToDTO(trackRepo.findByTrackId(trackId));
        return track;
    }

    public boolean updateTrackInfo(int trackId, String name, String size, String duration) {
        if (!check(name,size,duration)){
            return false;
        }
        else {
            TrackDTO track = trackConverter.convertToDTO(trackRepo.findByTrackId(trackId));
            track.setDuration(Long.valueOf(duration));
            track.setSize(Long.valueOf(size));
            track.setName(name);
            trackRepo.save(trackConverter.convertToEntity(track));
            return true;
        }
    }

    public void deleteTrack(int trackId) {
        TrackDTO track = trackConverter.convertToDTO(trackRepo.findByTrackId(trackId));
        trackRepo.delete(trackConverter.convertToEntity(track));
    }

    public void writeXML() throws JAXBException, FileNotFoundException {
        ClientDTO clientDTO = getUser();
        FileOutputStream stream = new FileOutputStream("src/main/resources/templates/outputFile.xml");
        JAXBContext context = JAXBContext.newInstance(ClientDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(clientDTO, stream);
    }

    public boolean readXML(MultipartFile file) throws IOException, JAXBException {
        ClientDTO clientDTO = getUser();
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("-uploaded")));
                stream.write(bytes);
                stream.close();
            }
            else {
                return false;
            }
            File prFile = new File("-uploaded");
            JAXBContext context = JAXBContext.newInstance(TracklistDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            TracklistDTO tracklist = (TracklistDTO) unmarshaller.unmarshal((File) prFile);
            for (TrackDTO track : tracklist.getTracks()) {
                if (!check(track.getName(), track.getSize().toString(), track.getDuration().toString())) {
                    return false;
                }
            }
            tracklist.setClient(clientDTO);
            tracklistRepo.save(tracklistConverter.convertToEntity(tracklist));
        } catch (Exception e) {
            return false;
        }
       /* for (TrackDTO track:tracklist.getTracks()){
            track.setId(trackRepo.findMaxTrackId() + 1);
            track.setTracklistId(tracklist.getId());
            trackRepo.save(trackConverter.convertToEntity(track));
        }
*/
        return true;
    }
}
