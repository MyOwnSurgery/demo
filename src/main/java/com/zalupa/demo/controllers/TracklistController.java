package com.zalupa.demo.controllers;
/*
import com.zalupa.demo.models.Client;
//import com.zalupa.demo.repo.ClientRepo;
//import com.zalupa.demo.Bean.ClientEJB;
import com.zalupa.demo.models.Client;
import com.zalupa.demo.models.Tracklist;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.repo.TracklistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class TracklistController {

    @Autowired
    private TracklistRepo repo;
    private HttpSession session;
    public Tracklist tracklist;
    public List<Tracklist> tracklists;
    public Client client;
    @GetMapping("/result")
    public String output(Model model){
        client = (Client) session.getAttribute("client");
        int id = client.getID();
        tracklists = repo.findByClientId(id);
        System.out.println(tracklists.size());
        return "/result";
    }

}*/