package com.memory.manager.CustomMemoryManager.service.impl;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.exception.MyException;
import com.memory.manager.CustomMemoryManager.service.PersitorService;
import com.memory.manager.CustomMemoryManager.util.UtilService;

public class PersistorServiceSparkImpl<R> extends PersistorServiceLocalFileImpl implements PersitorService {

	private JavaSparkContext javaSparkContext;

	@Override
	public <T extends Serializable> void persistObjects(List<Entity<T>> entities) throws MyException {
		if (entities.size() < 10000) {
			super.persistObjects(entities);
		} else {
			persistSpark(entities);
		}
	}

	private <T extends Serializable> void persistSpark(List<Entity<T>> entities) {

		JavaRDD<Entity<T>> javaRDD = javaSparkContext.parallelize(entities);

		javaRDD.map(new Function<Entity<T>, String>() {

			public String call(Entity<T> entity) throws Exception {

				FileOutputStream fos = new FileOutputStream(UtilService.folderPath + entity.getName() + ".txt");
				ObjectOutput ou = new ObjectOutputStream(fos);
				ou.writeObject(entity);
				ou.close();
				return "Object Persisted";

			}
		}).count();

	}

}
