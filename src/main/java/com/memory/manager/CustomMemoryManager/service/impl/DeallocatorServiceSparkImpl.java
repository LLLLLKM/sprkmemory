package com.memory.manager.CustomMemoryManager.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.memory.manager.CustomMemoryManager.exception.MyException;
import com.memory.manager.CustomMemoryManager.service.DeallocatorService;
import com.memory.manager.CustomMemoryManager.util.UtilService;

public class DeallocatorServiceSparkImpl extends DeallocatorServiceImpl implements DeallocatorService {

	private JavaSparkContext javaSparkContext = UtilService.javaSparkContext;

	@Override
	public void evictAll(List<String> names) throws MyException {
		if (names.size() < 2000) {
			super.evictAll(names);
		} else {
			evictAllSpark(names);
		}
	}

	private void evictAllSpark(List<String> names) {

		JavaRDD<String> javaRDD = javaSparkContext.parallelize(names);

		javaRDD.map(new Function<String, Boolean>() {

			public Boolean call(String entity) throws Exception {

				return Files.deleteIfExists(Paths.get("" + UtilService.folderPath + entity + ".txt"));

			}
		}).count();

	}
}
