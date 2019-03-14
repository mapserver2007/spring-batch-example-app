package com.example.app.batch.infrastructure.repository;

import com.example.app.batch.domain.object.User;
import com.example.app.batch.domain.repository.ExampleRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExampleRepositoryImpl extends ExampleRepository {
    @Override
    List<User> findById(@Param("id") int id);
}
