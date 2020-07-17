package com.zalupa.demo.repos;


import com.zalupa.demo.entities.Track;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepo extends CrudRepository<Track, Integer>{
    List<Track> findByTracklistId(int id);
    Track findByTrackId(int id);

}