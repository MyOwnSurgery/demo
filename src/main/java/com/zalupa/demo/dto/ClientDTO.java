package com.zalupa.demo.dto;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.converters.TracklistConverter;
import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.entities.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
@Component
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientDTO {

    private int id;
    private String login;
    private String name;
    @XmlTransient
    private String password;
    @XmlElementWrapper(name = "tracklists")
    @XmlElement(name = "tracklist")
    public List<TracklistDTO> tracklists;

    @XmlTransient
    @Autowired
    private ClientRepo clientRepo;
    @XmlTransient
    @Autowired
    private TracklistRepo tracklistRepo;
    @XmlTransient
    @Autowired
    private TrackRepo trackRepo;
    @XmlTransient
    public List<TrackDTO> tracks;
    @XmlTransient
    @Autowired
    public ClientConverter clientConverter;
    @XmlTransient
    @Autowired
    public TracklistConverter tracklistConverter;
    @XmlTransient
    @Autowired
    public TrackConverter trackConverter;
    @XmlTransient
    public Client entity;

    @XmlTransient
    private ModelMapper modelMapper;
    @XmlTransient
    private ClientDTO dto;

    public ClientDTO validate(String login, String password) {

        entity = clientRepo.findByLoginAndPassword(login, password);
        if (entity != null)
        dto = clientConverter.convertToDTO(entity);

        return dto;
    }

    public String getUserName() {
        return dto.getName();
    }
    public boolean check(String name,String size,String duration){

        try{
            long sizeVal = Long.valueOf(size);
            long durVal=Long.valueOf(duration);
        }
        catch(IllegalArgumentException e){
            return false;
        }
        if ((name != "") && ((Long.valueOf(size) > 0) && (Long.valueOf(size) < 10000)) && ((Long.valueOf(duration) > 0) && (Long.valueOf(duration) < 10000))){
            return true;
        }
        else return false;
    }
    public List<TracklistDTO> getUserLists() {
        tracklists = tracklistConverter.convertListToDTO(tracklistRepo.findByClientId(dto.getID()));

        for (int i = 0; i < tracklists.size(); i++) {
            int a = tracklists.get(i).getId();
            tracks = trackConverter.convertListToDTO(trackRepo.findByTracklistId(a));
            for (int j = 0; j < tracks.size(); j++) {
                tracklists.get(i).addTrack(tracks.get(j));
            }

        }
        dto.setTracklists(tracklists);
        return tracklists;
    }
    public void addTrack(TrackDTO track){

            track.setId(trackRepo.findMaxTrackId() + 1);

            trackRepo.save(trackConverter.convertToEntity(track));


    }
    public TrackDTO showTrackInfo(int trackId){
        TrackDTO track = trackConverter.convertToDTO(trackRepo.findByTrackId(trackId));
        return track;
    }
    public void updateTrackInfo(int trackId, String name, long size, long duration){
        TrackDTO track = trackConverter.convertToDTO(trackRepo.findByTrackId(trackId));
        track.setDuration(duration);
        track.setSize(size);
        track.setName(name);
        trackRepo.save(trackConverter.convertToEntity(track));
    }
    public void deleteTrack(int trackId){
        TrackDTO track = trackConverter.convertToDTO(trackRepo.findByTrackId(trackId));
        trackRepo.delete(trackConverter.convertToEntity(track));
    }
    public ClientDTO(int id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;

    }
    public void writeXML() throws JAXBException, FileNotFoundException {
        FileOutputStream stream = new FileOutputStream("src/main/resources/templates/outputFile.xml");

        JAXBContext context = JAXBContext.newInstance(ClientDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(dto, stream);


    }
    public void readXML(MultipartFile file) throws IOException, JAXBException {

        if (!file.isEmpty()) {

            byte[] bytes = file.getBytes();

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File("-uploaded")));
            stream.write(bytes);

            stream.close();


        }
        File prFile = new File("-uploaded");
        JAXBContext context = JAXBContext.newInstance(TracklistDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TracklistDTO tracklist = (TracklistDTO) unmarshaller.unmarshal((File) prFile);
        tracklist.setClient(dto);
        tracklistRepo.save(tracklistConverter.convertToEntity(tracklist));
       /* for (TrackDTO track:tracklist.getTracks()){
            track.setId(trackRepo.findMaxTrackId() + 1);
            track.setTracklistId(tracklist.getId());
            trackRepo.save(trackConverter.convertToEntity(track));
        }
*/

    }
    public ClientDTO(String login,String password){
        this.login = login;
        this.password = password;

    }
    public ClientDTO() {

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTracklists(List<TracklistDTO> tracklists){
        this.tracklists = tracklists;
    }
}

