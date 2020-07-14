package com.zalupa.demo.dto;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@SpringBootTest
class ClientDTOTest {
@Autowired
ClientDTO clientDTO;
@Autowired
@MockBean
private ClientRepo clientRepo;

    @Autowired
    @MockBean
    TrackRepo trackRepo;
    @MockBean
    @Autowired
    TrackConverter trackConverter;
@Autowired
ClientConverter clientConverter;
    @Test
    void validate() {

        clientDTO.validate("user1","1");
        Mockito.verify(clientRepo, Mockito.times(1)).findByLoginAndPassword("user1","1");

    }

    @Test
    void getUserLists() {
    }

    @Test
    void addTrack() {


    TrackDTO track = new TrackDTO(1488, 3, "track", 200, 200);
    clientDTO.addTrack(track);

    Mockito.verify(trackConverter, Mockito.times(1)).convertToEntity(track);
    Mockito.verify(trackRepo, Mockito.times(1)).save(ArgumentMatchers.any(Track.class));



    }

    @Test
    void showTrackInfo() {
    }

    @Test
    void updateTrackInfo() {
    }

    @Test
    void deleteTrack() {
    }
}