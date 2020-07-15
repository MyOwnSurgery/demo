package com.zalupa.demo.repo;


import com.zalupa.demo.entities.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepo extends CrudRepository<Track, Integer>{
    List<Track> findByTracklistId(int id);
    @Query(value = "select max(trackId) from Track")
    int findMaxTrackId();
    Track findByTrackId(int id);
}