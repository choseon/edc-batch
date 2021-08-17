package external.data.collection.batch.cmmn.service;

import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class CmmnService {

    private String jobGroupId;
    private String jobId;


    public CmmnService(String jobId) {
        this.jobId = jobId;
    }

    public String getApiUrl(String url, String... params) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("crtfcKey", crtfcKey)
                .queryParam("dataType", dataType);
        return uri.toUriString();
    }

    /**
     * tsv 수집파일 생성
     */
    public <T> void createCletTsvFile(List<T> list) {

    }

    /**
     * tsv 로그파일 생성
     */
    public void createLogTsvFile() {

    }

    /**
     * json 수집파일 생성
     */
    public void createCletJsonFile() {

    }

    /**
     * json 로그파일 생성
     */
    public void createLogJsonFile() {

    }
}
