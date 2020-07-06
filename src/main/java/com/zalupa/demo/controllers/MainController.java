package com.zalupa.demo.controllers;

import com.zalupa.demo.dto.ClientModel;
import com.zalupa.demo.entities.Client;
import com.zalupa.demo.entities.Track;
import com.zalupa.demo.entities.Tracklist;
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

@Controller
public class MainController {

public ClientModel dto;

    @GetMapping("/")
    public String home(Model model) {

        return "/index";
    }

    @PostMapping("/index")
    public String validate(@RequestParam String login, @RequestParam String password, Model model){
        if (dto.validate(login, password) != null) {
            return "redirect:/result";
        }
        else
            return "/index";
    }

    @GetMapping("/result")
    public String output(Model model){
        model.addAttribute("name", dto.getUserName());
        model.addAttribute("tracklists",dto.getUserLists());
        return "/result";
    }
    @GetMapping("/result/{id}")
    public String goToInsert(Model model, @PathVariable(value = "id") int id ){
        return "insert";
    }
    @PostMapping("/result/{id}")
    public String Add(Model model, @PathVariable(value = "id") int tracklistId, @RequestParam String name, @RequestParam long size, @RequestParam long duration){
        dto.addTrack(tracklistId,name,size,duration);
        return "redirect:/result";


    }
    @GetMapping("/result/update/{trackId}")
    public String goToUpdate(Model model, @PathVariable(value = "trackId") int trackId ){
        model.addAttribute("track",dto.showTrackInfo(trackId));
        return "update";
    }
    @PostMapping("/result/update/{trackId}")
    public String Update(Model model, @PathVariable(value = "trackId") int trackId, @RequestParam String name, @RequestParam long size, @RequestParam long duration ){
        dto.updateTrackInfo(trackId,name,size,duration);
        return "redirect:/result";
    }
    @PostMapping("/result/remove/{trackId}")
    public String Remove(Model model, @PathVariable(value = "trackId") int trackId){
        dto.deleteTrack(trackId);
        return "redirect:/result";
    }



}