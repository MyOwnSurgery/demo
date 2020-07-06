package com.zalupa.demo.repo;


import com.zalupa.demo.entities.Tracklist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TracklistRepo extends CrudRepository<Tracklist, Integer>{
    List<Tracklist> findByClientId(int id);
}