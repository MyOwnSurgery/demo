package com.zalupa.demo.repo;


import com.zalupa.demo.models.Client;
import com.zalupa.demo.models.Track;
import com.zalupa.demo.models.Tracklist;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TrackRepo extends CrudRepository<Track, Integer>{
    List<Track> findByTrackId(int id);
    Track findByName(String name);
}