package com.example.app.batch.domain.repository;

import com.example.app.batch.domain.object.User;

import java.util.List;

public interface ExampleRepository {
    List<User> findById(int id);
}
