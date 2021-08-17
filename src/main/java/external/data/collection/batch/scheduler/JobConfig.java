package external.data.collection.batch.scheduler;

import external.data.collection.batch.jobs.biz.biz001m.Biz001mTasklet;
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
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "${scheduler.cron}")
    public void scheduler() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("cletDt", LocalDateTime.now().toString()).toJobParameters();
        jobLauncher.run(dailyJob(),jobParameters);
    }

    @Bean
    public Job dailyJob() {

        return jobBuilderFactory.get("dailyJob").start(dailyStep()).build();
    }

    @Bean
    public Step dailyStep() {
        return stepBuilderFactory.get("dailyStep").tasklet(biz001mTasklet()).build();
    }

    @Bean
    public Biz001mTasklet biz001mTasklet() {
        return new Biz001mTasklet();
    }

}
