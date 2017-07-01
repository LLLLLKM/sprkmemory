package com.memory.manager.CustomMemoryManager.service.impl;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.exception.MyException;
import com.memory.manager.CustomMemoryManager.service.FetcherService;
import com.memory.manager.CustomMemoryManager.util.UtilService;

public class FetcherServiceSparkImpl extends FetcherServiceImpl implements FetcherService {

	private JavaSparkContext javaSparkContext = UtilService.javaSparkContext;

	@Override
	public <T extends Serializable> List<Entity<T>> getAll(List<String> names) throws MyException {
		if (names.size() < 2000) {
			return super.getAll(names);
		} else {
			return getAllSpark(names);
		}
	}

	private <T extends Serializable> List<Entity<T>> getAllSpark(List<String> names) {

		final List<Entity<T>> list = new ArrayList<Entity<T>>();

		JavaRDD<String> javaRDD = javaSparkContext.parallelize(names);
		javaRDD.map(new Function<String, Entity<T>>() {
			Entity<T> entity;

			public Entity<T> call(String name) throws Exception {

				FileInputStream fileInputStream = new FileInputStream("" + UtilService.folderPath + name + ".txt");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				entity = (Entity<T>) objectInputStream.readObject();
				objectInputStream.close();

				list.add(entity);
				return entity;
			}

		});
		return list;
	}

}
