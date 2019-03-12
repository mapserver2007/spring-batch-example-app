package com.example.app.batch.common;

import lombok.Getter;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;

public class CustomBatchConfigurer extends DefaultBatchConfigurer {

    private JobRepository jobRepository;
    private JobExplorer jobExplorer;
    private JobLauncher jobLauncher;

    public CustomBatchConfigurer() {
        jobRepositorySetting();
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

    private void jobRepositorySetting() {
        MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean();
        try {
            this.jobRepository = jobRepositoryFactory.getObject();
            MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
            this.jobExplorer = jobExplorerFactory.getObject();
            SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
            jobLauncher.setJobRepository(jobRepository);
            jobLauncher.afterPropertiesSet();
            this.jobLauncher = jobLauncher;

        } catch (Exception ignore) {
            // ignore
        }
    }

}
