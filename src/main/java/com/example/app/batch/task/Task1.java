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
        List<User> users = exampleService.getUserList(1);
        System.out.println("step1-db: " + users.get(0).getName());

        return RepeatStatus.FINISHED;
    }
}
