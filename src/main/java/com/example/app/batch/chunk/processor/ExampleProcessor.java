package com.example.app.batch.chunk.processor;

import com.example.app.batch.domain.object.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExampleProcessor implements ItemProcessor<User, User> {
    /**
     * processメソッドはReaderで入力されたデータが空の場合
     * 空のデータが渡ってくるのではなくそもそも呼ばれない。
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public User process(User user) {
        user.setName("processed-" + user.getName());
        System.out.println(user.getName());

        System.out.println("step3: processor");

        return user;
    }
}
