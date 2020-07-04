package com.zalupa.demo.controllers;

/*import com.zalupa.demo.models.Client;
//import com.zalupa.demo.repo.ClientRepo;
//import com.zalupa.demo.Bean.ClientEJB;
import com.zalupa.demo.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class ClientController {

@Autowired
private ClientRepo repo;

public Client client;
private HttpSession session;



    @PostMapping("/index")
    public String validate(@RequestParam String login, @RequestParam String password, Model model){
            client = repo.findByLoginAndPassword(login,password);
            if (client != null) {
                session.setAttribute("client", client);
                return "/result";
            }
            else
                return "/index";
    }

}
*/