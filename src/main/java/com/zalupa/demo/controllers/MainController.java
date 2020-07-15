package com.zalupa.demo.controllers;

import com.zalupa.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.*;

@Controller
public class MainController {

    @Autowired
    private Service service;

    private boolean logError;
    private boolean upError;
    private boolean addError;
    private boolean xmlError;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("error", "");
        return "/index";
    }

    @GetMapping("/index")
    public String showErrors(Model model) {
        if (logError)
            model.addAttribute("error", "Wrong login or password");
        return "/index";
    }

    @PostMapping("/index")
    public String validate(@RequestParam String login, @RequestParam String password, Model model) {
        if (service.validate(login, password) != null) {
            return "redirect:/result";
        } else {
            logError = true;
            return "redirect:/index";
        }
    }

    @GetMapping("/result")
    public String output(Model model) throws JAXBException, FileNotFoundException {
        model.addAttribute("xmlError", "");
        if (xmlError) {
            model.addAttribute("xmlError", "Smth wrong with your file");
        }
        model.addAttribute("name", service.getUserName());
        model.addAttribute("tracklists", service.getUserLists());

        return "/result";
    }

    @GetMapping("/result/{id}")
    public String goToInsert(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("addError", "");
        if (addError) {
            model.addAttribute("addError", "Wrong data");
        }
        return "/insert";
    }

    @PostMapping("/result/{id}")
    public String Add(Model model, @PathVariable(value = "id") int tracklistId, @RequestParam String name, @RequestParam String size, @RequestParam String duration) {
        if (service.addTrack(tracklistId, name, size, duration)) {
            addError = false;
            return "redirect:/result";
        } else {
            addError = true;
            return goToInsert(model, tracklistId);
        }
    }

    @GetMapping("/result/update/{trackId}")
    public String goToUpdate(Model model, @PathVariable(value = "trackId") int trackId) {
        model.addAttribute("track", service.showTrackInfo(trackId));
        model.addAttribute("upError", "");
        if (upError) {
            model.addAttribute("upError", "Wrong data");
        }
        return "/update";
    }

    @PostMapping("/result/update/{trackId}")
    public String Update(Model model, @PathVariable(value = "trackId") int trackId, @RequestParam String name, @RequestParam String size, @RequestParam String duration) {
        if (service.updateTrackInfo(trackId, name, size, duration)) {
            upError = false;
            return "redirect:/result";
        } else {
            upError = true;
            return goToUpdate(model, trackId);
        }
    }

    @PostMapping("/result/remove/{trackId}")
    public String Remove(Model model, @PathVariable(value = "trackId") int trackId) {
        service.deleteTrack(trackId);
        return "redirect:/result";
    }

    @PostMapping("/upload")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(Model model, @RequestParam("file") MultipartFile file) throws IOException, JAXBException {
        if (!service.readXML(file)) {
            xmlError = true;
        } else {
            xmlError = false;
        }
        return "redirect:/result";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadFile() throws IOException, JAXBException {
        service.writeXML();
        String filename = "C:/Users/Алексей/Desktop/demo/src/main/resources/templates/outputFile.xml";
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/xml")).body(resource);
        return responseEntity;
    }


}