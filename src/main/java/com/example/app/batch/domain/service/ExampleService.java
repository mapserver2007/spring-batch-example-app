package com.example.app.batch.domain.service;

import com.example.app.batch.domain.object.User;
import com.example.app.batch.domain.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {
    @Autowired
    private ExampleRepository exampleRepository;

    public List<User> getUserList(int id) {
        return exampleRepository.findById(id);
    }
}
