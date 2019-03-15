package com.example.app.batch.chunk.reader;

import com.example.app.batch.domain.object.User;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExampleReader {
    @Autowired
    private SqlSessionFactoryBean sessionFactoryBean;

    public MyBatisCursorItemReader getReader() throws Exception {
        MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);

        reader.setSqlSessionFactory(sessionFactoryBean.getObject());
        reader.setQueryId("com.example.app.batch.infrastructure.repository.ExampleRepositoryImpl.findById");
        reader.setParameterValues(params);

        System.out.println("step3: reader");

        return reader;
    }
}
