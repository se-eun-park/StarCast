//package com.mobyeoldol.data_collector.application.batch.config;
//
//import com.mobyeoldol.data_collector.application.hadoop.reader.ShortAndMediumTermForecastApiReader;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    // ItemReader 정의 (정제된 데이터를 리스트로 전달)
//    @Bean
//    public ListItemReader<WeatherData> reader(List<WeatherData> refinedWeatherData) {
//        return new ListItemReader<>(refinedWeatherData);
//    }
//
//    // 데이터 처리 로직을 ItemProcessor로 정의
//    @Bean
//    public ItemProcessor<WeatherData, WeatherData> processor() {
//        return item -> item;  // 이미 데이터는 정제되었으므로 그대로 넘김
//    }
//
//    // MySQL에 데이터를 저장하는 ItemWriter 정의
//    @Bean
//    public JdbcBatchItemWriter<WeatherData> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<WeatherData>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO weather_data (date, location, temperature, humidity, rainfall) " +
//                        "VALUES (:date, :location, :temperature, :humidity, :rainfall)")
//                .dataSource(dataSource)
//                .build();
//    }
//
//    // Step 정의
//    @Bean
//    public Step weatherDataStep(StepBuilderFactory stepBuilderFactory, ListItemReader<WeatherData> reader,
//                                ItemProcessor<WeatherData, WeatherData> processor, JdbcBatchItemWriter<WeatherData> writer) {
//        return stepBuilderFactory.get("weatherDataStep")
//                .<WeatherData, WeatherData>chunk(10)
//                .reader(reader)
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }
//
//    // Job 정의
//    @Bean
//    public Job weatherDataJob(JobBuilderFactory jobBuilderFactory, Step weatherDataStep) {
//        return jobBuilderFactory.get("weatherDataJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(weatherDataStep)
//                .end()
//                .build();
//    }
//    /*
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//
//    public BatchConfig(JobRepository jobRepository) {
//        this.jobRepository = jobRepository;
//        this.transactionManager = new ResourcelessTransactionManager();
//    }
//
//    @Bean
//    public Job weatherDataProcessingJob() {
//        return new JobBuilder("weatherDataProcessingJob", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(weatherDataStep())
//                .build();
//    }
//
//    @Bean
//    public Step weatherDataStep() {
//        return new StepBuilder("weatherDataStep", jobRepository)
//                .<String, String>chunk(10, transactionManager)
//                .reader(getShortAndMediumTermForecastApiReader())
//                .processor(passThroughProcessor())
//                .writer(consoleWriter())
//                .build();
//    }
//
//    // 임시 Processor: 데이터를 그대로 통과
//    @Bean
//    public ItemProcessor<String, String> passThroughProcessor() {
//        return item -> item;
//    }
//
//    // 임시 Writer: 데이터를 콘솔에 출력
//    @Bean
//    public ItemWriter<String> consoleWriter() {
//        return items -> items.forEach(System.out::println);
//    }
//
//    @Bean
//    public ItemReader<String> getShortAndMediumTermForecastApiReader() {
//        return new ShortAndMediumTermForecastApiReader();
//    }
//
////    @Bean
////    public ItemProcessor<String, String> forecastProcessor() {
////        return new ForecastProcessor();
////    }
////
////    @Bean
////    public ItemReader<String> getMoonriseAndMoonsetByLocationApiReader() {
////        return new MoonriseAndMoonsetByLocationApiReader();
////    }
////
////    @Bean
////    public ItemProcessor<String, String> lunarPhenomenaDataProcessor() {
////        return new LlunarPhenomenaProcessor();
////    }
////
////    @Bean
////    public ItemWriter<String> hadoopWriter() {
////        return new HadoopWriter();
////    }
//     */
//}