package com.mobyeoldol.data_collector.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobyeoldol.data_collector.application.dto.ShortTermWeatherDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WeatherDataService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    // WebClient와 ObjectMapper를 주입받는 생성자
    public WeatherDataService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0").build();
        this.objectMapper = objectMapper;
    }

    // 하루에 한 번, 기상청 API에서 데이터를 받아 JSON 파일로 저장하는 메소드
    public void fetchAndStoreShortTermWeatherData() {

        LocalDate now = LocalDate.now(); //ex) 2019-11-13
        String baseDate = now.toString().substring(0, 10).replace("-", "");
        int nx = 14;
        int ny = 75;

        // JSON 데이터를 받아와 shortTermWeatherDto 객체로 변환
        ShortTermWeatherDto shortTermWeatherDto = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getVilageFcst")
                        .queryParam("serviceKey", "hehJJJOle7xWEAtg49JmBL42UBj8t5AvqgkuWubMkjLhnGL%2FLDjyEtGac8Vd0VFGgEiA6V0RfD1rm7ye%2Fr4jjQ%3D%3D")
                        .queryParam("numOfRows", 2000)
                        .queryParam("pageNo", 1)
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", "2000")
                        .queryParam("nx", nx)
                        .queryParam("ny", ny)
                        .build()
                )
                .retrieve()
                .bodyToMono(ShortTermWeatherDto.class)  // 응답을 Java 객체로 변환
                .block();  // 동기식으로 실행

        // 데이터를 CSV 파일로 저장
        /*
        try (FileWriter fileWriter = new FileWriter("short_term_data.csv")) {
            // CSV 헤더 작성
            fileWriter.append("Date,Location,Temperature,Humidity,Rainfall\n");

            // Main 데이터 작성
            fileWriter.append(weatherData.getDate()).append(",")
                    .append(weatherData.getLocation()).append(",")
                    .append(String.valueOf(weatherData.getTemperature())).append(",")
                    .append(String.valueOf(weatherData.getHumidity())).append(",")
                    .append(String.valueOf(weatherData.getRainfall())).append("\n");

            // Forecast 데이터 작성 (만약 여러 시간이 포함된 예측 데이터가 있을 경우)
            if (weatherData.getForecast() != null) {
                fileWriter.append("Time,Temperature\n"); // Forecast 부분 헤더
                for (shortTermWeatherDto.Forecast forecast : weatherData.getForecast()) {
                    fileWriter.append(forecast.getTime()).append(",")
                            .append(String.valueOf(forecast.getTemperature())).append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // 파일 저장 중 오류가 발생하면 로그 출력
        }

         */
    }
}
