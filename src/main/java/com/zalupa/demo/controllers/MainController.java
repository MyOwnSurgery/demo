package com.zalupa.demo.controllers;

import com.zalupa.demo.models.Client;
import com.zalupa.demo.models.Track;
import com.zalupa.demo.models.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TrackRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private TracklistRepo tracklistRepo;
    @Autowired
    private TrackRepo trackRepo;

public Client client;
    public Tracklist tracklist;
    public List<Tracklist> tracklists;
    public List<Track> tracks;
    public int num;
    @GetMapping("/")
    public String home(Model model) {

        return "/index";
    }

    @PostMapping("/index")
    public String validate(@RequestParam String login, @RequestParam String password, Model model){
        client = clientRepo.findByLoginAndPassword(login,password);
        if (client != null) {
            return "redirect:/result";
        }
        else
            return "/index";
    }

    @GetMapping("/result")
    public String output(Model model){
        model.addAttribute("name", client.getName());
        int id = client.getID();
        tracklists = tracklistRepo.findByClientId(id);

for (int i=0; i < tracklists.size();i++){
    int a = tracklists.get(i).getId();
    tracks = trackRepo.findByTracklistId(a);
    for (int j = 0; j < tracks.size();j++){
        tracklists.get(i).addTrack(tracks.get(j));
    }

}

        model.addAttribute("tracklists",tracklists);
        return "/result";
    }
    @GetMapping("/result/{id}")
    public String goToInsert(Model model, @PathVariable(value = "id") int id ){
        return "insert";
    }
    @PostMapping("/result/{id}")
    public String Add(Model model, @PathVariable(value = "id") int tracklistId, @RequestParam String name, @RequestParam long size, @RequestParam long duration){
        int id = trackRepo.findMaxTrackId() + 1;
        System.out.println(id);
        Track track = new Track(id,tracklistId,name,size,duration);
        trackRepo.save(track);
        return "redirect:/result";


    }
    @GetMapping("/result/update/{trackId}")
    public String goToUpdate(Model model, @PathVariable(value = "trackId") int trackId ){
        Track track = trackRepo.findByTrackId(trackId);
        model.addAttribute("track",track);
        return "update";
    }
    @PostMapping("/result/update/{trackId}")
    public String Update(Model model, @PathVariable(value = "trackId") int trackId, @RequestParam String name, @RequestParam long size, @RequestParam long duration ){
        Track track = trackRepo.findByTrackId(trackId);
        track.setDuration(duration);
        track.setSize(size);
        track.setName(name);
        trackRepo.save(track);
        return "redirect:/result";
    }
    @PostMapping("/result/remove/{trackId}")
    public String Remove(Model model, @PathVariable(value = "trackId") int trackId){
        Track track = trackRepo.findByTrackId(trackId);
        trackRepo.delete(track);
        return "redirect:/result";
    }



}