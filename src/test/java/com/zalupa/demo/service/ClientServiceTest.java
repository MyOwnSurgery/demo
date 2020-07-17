package com.zalupa.demo.service;

import com.zalupa.demo.converters.impl.ClientConverter;
import com.zalupa.demo.repo.ClientRepo;
import com.zalupa.demo.service.impl.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class ClientServiceTest {
    @Autowired
    public ClientService service;
    @Autowired
    @MockBean
    public ClientRepo repo;
    @Autowired
    ClientConverter converter;
    @Test
    void getByLoginAndPassword() {
        service.getByLoginAndPassword("user1", "1");
        Mockito.verify(repo, Mockito.times(1)).findByLoginAndPassword("user1", "1");
    }

    @Test
    void setUserLists() {
    }
}