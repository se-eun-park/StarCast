package com.mobyeoldol.data_collector.presentation.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataCollector {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job weatherDataProcessingJob;


    @Scheduled(cron = "0 * * * * ?")
    public void test() {
        log.info("매 분마다 실행되도록 설정 / 테스트");

//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timestamp", System.currentTimeMillis()) // 고유한 JobParameters 생성
//                    .toJobParameters();
//
//            jobLauncher.run(weatherDataProcessingJob, jobParameters);
//            log.info("단기 예보 Job이 실행되었습니다.");
//
//        } catch (Exception e) {
//            log.error("Job 실행 중 오류 발생: ", e);
//        }
    }

    @Scheduled(cron = "0 10 20 * * ?")
    public void getWeatherForecast() throws Exception {
        log.info("매일 오후 8시 10분에 실행 / 단기예보와 중기예보 업데이트");
    }

    @Scheduled(cron = "0 0 0 1 1 ?")
    public void getMoonAstronomicalPhenomena() throws Exception {
        log.info("매년 1월 1일 자정에 실행 / 월령과 천문현상 업데이트");
    }

    @Scheduled(cron = "0 0 0 * * 1")
    public void getLunarPhenomenaByLocation() throws Exception {
        log.info("매주 월요일 자정에 실행 / 위치별 출몰시각(월출/월몰) 업데이트");
    }
}
