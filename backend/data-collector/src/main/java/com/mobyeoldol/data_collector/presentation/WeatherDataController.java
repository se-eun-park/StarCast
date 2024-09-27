//package com.mobyeoldol.data_collector.presentation;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/weather")
//public class WeatherDataController {
//
//    private final WeatherDataService weatherDataService;
//
//    public WeatherDataController(WeatherDataService weatherDataService) {
//        this.weatherDataService = weatherDataService;
//    }
//
//    @GetMapping("/fetch")
//    public String fetchWeatherData() {
//        weatherDataService.fetchAndStoreWeatherData();
//        return "Weather data fetched and stored!";
//    }
//}
