//package com.mobyeoldol.data_collector.domain.mapreduce;
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Mapper;
//
//import java.io.IOException;
//
//public class WeatherMapper extends Mapper<Object, Text, Text, IntWritable> {
//
//    private final static IntWritable temperature = new IntWritable();
//    private Text date = new Text();
//
//    @Override
//    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
//        String[] fields = value.toString().split(",");
//
//        if (fields.length > 2) {
//            date.set(fields[0]);  // 날짜
//            int temp = Integer.parseInt(fields[2]);  // 온도
//            temperature.set(temp);
//            context.write(date, temperature);  // 날짜를 키로, 온도를 값으로 출력
//        }
//    }
//}
//
