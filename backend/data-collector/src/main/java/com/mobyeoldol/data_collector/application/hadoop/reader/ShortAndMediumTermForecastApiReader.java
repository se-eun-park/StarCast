package com.mobyeoldol.data_collector.application.hadoop.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ShortAndMediumTermForecastApiReader implements ItemReader<String> {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private boolean dataFetched = false;
    private final List<String> results = new ArrayList<>();

    @Value("${api.key}")
    private String apiKey;

    public ShortAndMediumTermForecastApiReader() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String read() {
        if (!dataFetched) {
            log.info("기상청 API에 넣을 nx, ny를 DB에서 가져와야 함");
            int nx = 55;
            int ny = 127;

            String baseDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String baseTime = "2000";

            log.info("기상청 API 호출");
            String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                    + "?serviceKey=" + apiKey
                    + "&numOfRows=2000"
                    + "&pageNo=1"
                    + "&base_date=" + baseDate
                    + "&base_time=" + baseTime
                    + "&nx="+nx
                    + "&ny="+ny
                    + "&dataType=JSON";

            try {
                String response = restTemplate.getForObject(url, String.class);
                JsonNode root = objectMapper.readTree(response);
                JsonNode items = root.path("response").path("body").path("items").path("item");

                for (JsonNode item : items) {
                    String category = item.path("category").asText();
                    if ("SKY".equals(category) || "PTY".equals(category) || "REH".equals(category)) {
                        String forecastDate = item.path("fcstDate").asText(); // 예보 일자
                        String forecastTime = item.path("fcstTime").asText(); // 예보 시각
                        String forecastValue = item.path("fcstValue").asText(); // 예보 값
                        String formattedData = "Date: " + forecastDate + ", Time: " + forecastTime + ", " + category + ": " + forecastValue;
                        results.add(formattedData); // 리스트에 저장
                        log.info("추출된 데이터: " + formattedData); // 로깅
                    }
                }
                dataFetched = true; // 플래그 설정
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!results.isEmpty()) {
            return results.remove(0);
        } else {
            return null;
        }
    }
}
