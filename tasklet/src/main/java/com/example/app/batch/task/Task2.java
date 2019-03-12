package com.example.app.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class Task2 implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
        System.out.println("tasklet2");
        return RepeatStatus.FINISHED;
    }
}
