package com.mobyeoldol.data_collector;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataCollectorApplication {

//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Autowired
//	private Job job;
//
//	@Autowired
//	private WeatherDataService weatherDataService;
//
//	@Autowired
//	private WeatherDataProcessor weatherDataProcessor;
//
//	@Override
//	public void run(String... args) throws Exception {
//		// API 호출을 통해 데이터를 수집
//		WeatherDataResponse response = weatherDataService.fetchWeatherData();
//
//		// 데이터를 정제
//		List<WeatherData> refinedData = weatherDataProcessor.refineData(response);
//
//		// Spring Batch Job 실행
//		jobLauncher.run(job, new org.springframework.batch.core.JobParameters());
//	}

	public static void main(String[] args) {
		SpringApplication.run(DataCollectorApplication.class, args);
	}

}
