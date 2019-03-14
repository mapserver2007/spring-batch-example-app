package com.example.app.batch.chunk.processor;

import com.example.app.batch.domain.object.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExampleProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) {
        user.setName("processed-" + user.getName());
        System.out.println(user.getName());

        System.out.println("step3: processor");

        return user;
    }
}
