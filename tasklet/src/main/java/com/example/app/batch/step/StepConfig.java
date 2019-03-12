package com.example.app.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {
    @Autowired
    private Tasklet task1;

    @Autowired
    private Tasklet task2;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(task1)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(task2)
                .build();
    }
}
