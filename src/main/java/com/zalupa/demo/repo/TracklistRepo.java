package com.zalupa.demo.repo;


import com.zalupa.demo.models.Client;
import com.zalupa.demo.models.Tracklist;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TracklistRepo extends CrudRepository<Tracklist, Integer>{
    List<Tracklist> findByClientId(int id);
}