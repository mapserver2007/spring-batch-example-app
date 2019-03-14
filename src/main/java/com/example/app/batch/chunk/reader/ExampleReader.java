package com.example.app.batch.chunk.reader;

import com.example.app.batch.domain.object.User;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleReader {
    @Autowired
    private SqlSessionFactoryBean sessionFactoryBean;

    public MyBatisCursorItemReader getReader() throws Exception {
        MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<>();
        reader.setSqlSessionFactory(sessionFactoryBean.getObject());
        reader.setQueryId("com.example.app.batch.infrastructure.repository.ExampleRepositoryImpl.findById");

        System.out.println("step3: reader");

        return reader;
    }
}
