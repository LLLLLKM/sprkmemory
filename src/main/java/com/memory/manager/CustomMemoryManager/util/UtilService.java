package com.memory.manager.CustomMemoryManager.util;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class UtilService {

	public static String folderPath = "";

	public static SparkConf sparkConf = new SparkConf().setAppName("cutomMemoryManager").setMaster("local")
			.set("spark.executor.memory", "1g").set("spark.cores.max", "10");
	public static JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
}
