package external.data.collection.batch.scheduler;


import external.data.collection.batch.jobs.big.big001m.Big001mTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BigJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final JobLauncher jobLauncher;

//    @Scheduled(cron = "*/5 * * * * ?") // 5초마다
    public void scheduler() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("cletDt", LocalDateTime.now().toString()).toJobParameters();
        jobLauncher.run(bigJob(),jobParameters);
    }

    @Bean
    public Job bigJob() {
        return jobBuilderFactory.get("bigJob").start(big001mStep()).build();
    }

    @Bean
    public Step big001mStep() {

        return stepBuilderFactory.get("big001mStep").tasklet(new Big001mTasklet()).build();
    }
}
