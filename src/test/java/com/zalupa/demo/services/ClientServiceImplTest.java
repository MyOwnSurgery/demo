package com.zalupa.demo.services;

import com.zalupa.demo.converters.ClientConverter;
import com.zalupa.demo.converters.impl.ClientConverterImpl;
import com.zalupa.demo.repos.ClientRepo;
import com.zalupa.demo.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class ClientServiceImplTest {
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