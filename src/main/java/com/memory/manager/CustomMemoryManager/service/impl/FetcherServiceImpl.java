package com.memory.manager.CustomMemoryManager.service.impl;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.exception.MyException;
import com.memory.manager.CustomMemoryManager.service.FetcherService;
import com.memory.manager.CustomMemoryManager.util.UtilService;

public class FetcherServiceImpl implements FetcherService {

	public <T extends Serializable> Entity<T> get(String name) throws MyException {
		Entity<T> entity = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(UtilService.folderPath + name + ".txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			entity = (Entity<T>) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			throw new MyException(e.getMessage(), e);
		}

		return entity;
	}

	public <T extends Serializable> List<Entity<T>> getAll(List<String> names) throws MyException {
		List<Entity<T>> entities = new ArrayList<Entity<T>>();
		try {

			for (String name : names) {
				Entity<T> entity = get(name);
				entities.add(entity);
			}

		} catch (Exception e) {
			throw new MyException(e.getMessage(), e);
		}
		return entities;

	}

}
