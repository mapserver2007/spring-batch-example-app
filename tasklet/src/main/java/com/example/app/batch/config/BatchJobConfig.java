package com.example.app.batch.config;

import com.example.app.batch.listener.ExampleJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
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
public class BatchJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Step step1;

    @Autowired
    private Step step2;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .listener(new ExampleJobListener())
                .start(step1)
                .next(step2)
                .build();
    }

    /**
     * この設定を入れるとjobRepositoryがIn-Memoryになる
     * @return
     */
    @Bean
    public DefaultBatchConfigurer batchConfigurer() {
        return new DefaultBatchConfigurer() {
            private JobRepository jobRepository;
            private JobExplorer jobExplorer;
            private JobLauncher jobLauncher;

            {
                MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean();
                try {
                    jobRepository = jobRepositoryFactory.getObject();
                    MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
                    jobExplorer = jobExplorerFactory.getObject();
                    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                    jobLauncher.setJobRepository(jobRepository);
                    jobLauncher.afterPropertiesSet();
                    this.jobLauncher = jobLauncher;

                } catch (Exception ignore) {
                    // ignore
                }
            }

            @Override
            public JobRepository getJobRepository() {
                return jobRepository;
            }

            @Override
            public JobExplorer getJobExplorer() {
                return jobExplorer;
            }

            @Override
            public JobLauncher getJobLauncher() {
                return jobLauncher;
            }
        };
    }
}
