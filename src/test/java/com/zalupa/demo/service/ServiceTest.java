package com.zalupa.demo.service;


import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.TrackConverter;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class ServiceTest {
    @Autowired
    public Service service;
    @Autowired
    @MockBean
    public ClientRepo clientRepo;

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
        service.validate("user1", "1");
        Mockito.verify(clientRepo, Mockito.times(1)).findByLoginAndPassword("user1", "1");

    }

    @Test
    void getUserLists() {
    }

    @Test
    void addTrack() {
        boolean is = service.addTrack(3, "track", "200", "200");
        Assert.assertTrue(is);
        Mockito.verify(trackConverter, Mockito.times(1)).convertToEntity(ArgumentMatchers.any(TrackDTO.class));
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

    @Test
    void writeXML() {
    }

    @Test
    void readXML() {
    }
}