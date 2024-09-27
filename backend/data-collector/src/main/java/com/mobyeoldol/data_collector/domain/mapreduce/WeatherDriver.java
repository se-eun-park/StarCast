//package com.mobyeoldol.data_collector.domain.mapreduce;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.util.GenericOptionsParser;
//
//public class WeatherDriver {
//
//    public static void main(String[] args) throws Exception {
//        Configuration conf = new Configuration();
//        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//
//        if (otherArgs.length < 2) {
//            System.err.println("Usage: WeatherDriver <in> <out>");
//            System.exit(2);
//        }
//
//        Job job = Job.getInstance(conf, "max temperature");
//        job.setJarByClass(WeatherDriver.class);
//        job.setMapperClass(WeatherMapper.class);
//        job.setReducerClass(WeatherReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(IntWritable.class);
//
//        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
//
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//    }
//}
//
