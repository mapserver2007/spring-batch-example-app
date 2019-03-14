package com.example.app.batch.chunk.writer;

import com.example.app.batch.domain.object.User;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleWriter {
    @Autowired
    private SqlSessionFactoryBean sessionFactoryBean;

    public MyBatisBatchItemWriter<User> getWriter() throws Exception {
        MyBatisBatchItemWriter<User> writer = new MyBatisBatchItemWriter<>();
        writer.setSqlSessionFactory(sessionFactoryBean.getObject());
        writer.setStatementId("com.example.app.batch.infrastructure.repository.ExampleRepositoryImpl.add");

        System.out.println("step3: writer");

        return writer;
    }
}
