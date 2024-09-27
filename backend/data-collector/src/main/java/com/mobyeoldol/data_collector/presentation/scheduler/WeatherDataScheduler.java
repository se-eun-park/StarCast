//package com.mobyeoldol.data_collector.presentation.scheduler;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WeatherDataScheduler {
//
//    private final WeatherDataService weatherDataService;
//
//    public WeatherDataScheduler(WeatherDataService weatherDataService) {
//        this.weatherDataService = weatherDataService;
//    }
//
//    // 매일 오후 8시에 기상청 API 데이터 수집
//    @Scheduled(cron = "0 0 20 * * ?")
//    public void scheduleWeatherDataFetch() {
//        weatherDataService.fetchAndStoreWeatherData();
//    }
//}
