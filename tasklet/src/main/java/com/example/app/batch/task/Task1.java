package com.example.app.batch.task;

import com.example.app.batch.domain.object.User;
import com.example.app.batch.domain.service.ExampleService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Task1 implements Tasklet {
    @Autowired
    private ExampleService exampleService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
        System.out.println("step1(tasklet)");

        // DB接続処理
        // MyBatisCursorItemReader とかを使ってアクセスする方法もあるが、
        // spring-bootと同じ方法でDBアクセスを書くほうがdomain/infrastructureを分離できる。
        List<User> users = exampleService.getUserList("dev");

        System.out.println("step1-db: " + users.get(0).getAccountId());

        return RepeatStatus.FINISHED;
    }
}
