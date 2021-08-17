package external.data.collection.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing // 배치 기능 활성화
@EnableScheduling // 스케쥴링 기능 활성화
@SpringBootApplication
public class EdcBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdcBatchApplication.class, args);
    }

}
