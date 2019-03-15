package com.example.app.batch.job;

import com.example.app.batch.common.CustomBatchConfigurer;
import com.example.app.batch.listener.ExampleJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EnableBatchProcessingはDataSourceを見に行く機能が入っており、
 * BATCH_JOB_INSTANCEテーブルにメタ情報を書き込みに行く。これを使ってジョブの再実行などができるようだが、
 * 規模的にジョブ全体を再実行すればだいたい済む話なのでIn-Memoryに差し替える予定
 */
@Configuration
@EnableBatchProcessing
public class JobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step step1;

    @Autowired
    private Step step2;

    @Autowired
    private Step step3;

    @Bean
    public Job job() {
        Flow flow = new FlowBuilder<Flow>("flow")
                .from(step1).next(step2).next(step3)
                .build();

        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(new ExampleJobListener())
                .start(flow)
                .end()
                .build();
    }

    /**
     * この設定を入れるとjobRepositoryがIn-Memoryになる
     * @return
     */
    @Bean
    public DefaultBatchConfigurer batchConfigurer() {
        return new CustomBatchConfigurer();
    }
}
