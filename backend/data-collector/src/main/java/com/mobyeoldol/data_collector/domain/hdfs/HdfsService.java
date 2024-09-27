//package com.mobyeoldol.data_collector.domain.hdfs;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.springframework.stereotype.Service;
//
//import java.io.FileInputStream;
//import java.net.URI;
//
//@Service
//public class HdfsService {
//
//    private final String hdfsUri = "hdfs://localhost:9000";
//
//    public void uploadFileToHdfs(String localFilePath, String hdfsDestinationPath) throws Exception {
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUri), conf);
//
//        try (FileInputStream in = new FileInputStream(localFilePath)) {
//            fs.copyFromLocalFile(new Path(localFilePath), new Path(hdfsDestinationPath));
//        } finally {
//            fs.close();
//        }
//    }
//}
