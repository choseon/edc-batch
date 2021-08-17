package external.data.collection.batch.jobs.biz.biz001m;

import com.fasterxml.jackson.databind.ObjectMapper;
import external.data.collection.batch.cmmn.util.FileUtil;
import external.data.collection.batch.jobs.biz.biz001m.vo.Biz001mVO;
import external.data.collection.batch.jobs.biz.biz001m.vo.ResultObjectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.Map;

@Slf4j
public class Biz001mTasklet implements Tasklet {

    @Value("${api.biz.url}")
    private String url;

    @Value("${api.biz.crtfcKey}")
    private String crtfcKey;

    @Value("${api.biz.dataType}")
    private String dataType;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info("START >>>> {}" ,this.getClass().getName());

        log.info("url >>> {}", url);
        log.info("ctrfcKey >>> {}", crtfcKey);
        log.info("dataType >>> {}", dataType);

        // UriComponentsBuilder 를 이용하여 url 링크 생성
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("crtfcKey", crtfcKey)
            .queryParam("dataType", dataType);

        log.info("uri >> {}", uri.toUriString());

        // RestTemplate을 이용하여 결과 저장
        RestTemplate restTemplate = new RestTemplate();
        Biz001mVO resultList = restTemplate.getForObject(uri.toUriString(), Biz001mVO.class);

        log.info("resultObjectVO.getJsonArray().size() >> {}", resultList.getJsonArray().size());
        for (Biz001mVO resultVO : resultList.getJsonArray()) {
            log.info("jsonArray.toString() >> {}", resultVO.toString());
        }

        // 수집 파일 생성
        FileUtil.createCletTsvFile(resultList.getJsonArray());


        log.info("END >>>> {}" ,this.getClass().getName());

        return RepeatStatus.FINISHED;
    }
}
