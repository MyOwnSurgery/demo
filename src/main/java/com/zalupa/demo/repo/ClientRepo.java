package com.zalupa.demo.repo;

import com.zalupa.demo.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepo extends CrudRepository<Client, Integer>{
Client findByLoginAndPassword(String login, String password);
}
