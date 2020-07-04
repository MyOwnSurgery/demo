package com.zalupa.demo.repo;

import com.zalupa.demo.models.Client;
import org.springframework.data.repository.CrudRepository;
public interface ClientRepo extends CrudRepository<Client, Integer>{
Client findByLoginAndPassword(String login, String password);
}
