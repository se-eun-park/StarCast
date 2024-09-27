//package com.mobyeoldol.data_collector.domain.mapreduce;
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Reducer;
//
//import java.io.IOException;
//
//public class WeatherReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
//
//    private IntWritable maxTemperature = new IntWritable();
//
//    @Override
//    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        int maxTemp = Integer.MIN_VALUE;
//
//        for (IntWritable value : values) {
//            maxTemp = Math.max(maxTemp, value.get());
//        }
//
//        maxTemperature.set(maxTemp);
//        context.write(key, maxTemperature);  // 날짜별 최고 기온을 출력
//    }
//}
