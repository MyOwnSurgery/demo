package com.zalupa.demo.services;

import com.zalupa.demo.converters.impl.TrackConverter;
import com.zalupa.demo.dto.TrackDTO;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.repos.TrackRepo;
import com.zalupa.demo.services.impl.TrackService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class TrackServiceTest {
    @Autowired
    public TrackService service;

    @Autowired
    @MockBean
    TrackRepo repo;
    @MockBean
    @Autowired
    TrackConverter converter;

    @Test
    void insert() {
        boolean is = service.insert(3, "track", "200", "200");
        Assert.assertTrue(is);
        Mockito.verify(converter, Mockito.times(1)).convertToEntity(ArgumentMatchers.any(TrackDTO.class));
        Mockito.verify(repo, Mockito.times(1)).save(ArgumentMatchers.any(Track.class));
    }

    @Test
    void showInfo() {
        service.showInfo(1);
        Mockito.verify(converter, Mockito.times(1)).convertToDTO(ArgumentMatchers.any(Track.class));
    }

    @Test
    void updateInfo() {
    }

    @Test
    void remove() {
    }

    @Test
    void find() {
    }

    @Test
    void save() {
    }
}