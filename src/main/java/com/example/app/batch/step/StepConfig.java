package com.example.app.batch.step;

import com.example.app.batch.chunk.processor.ExampleProcessor;
import com.example.app.batch.chunk.reader.ExampleReader;
import com.example.app.batch.chunk.writer.ExampleWriter;
import com.example.app.batch.domain.object.User;
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
    private ExampleReader exampleReader;

    @Autowired
    private ExampleWriter exampleWriter;

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

    @Bean
    public Step step3() throws Exception {
        return stepBuilderFactory.get("step3")
                .<User, User> chunk(3)
                .reader(exampleReader.getReader())
                .processor(new ExampleProcessor())
                .writer(exampleWriter.getWriter())
                .build();
    }
}
