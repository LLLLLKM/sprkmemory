package com.memory.manager.CustomMemoryManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.service.DeallocatorService;
import com.memory.manager.CustomMemoryManager.service.PersitorService;
import com.memory.manager.CustomMemoryManager.service.impl.DeallocatorServiceImpl;
import com.memory.manager.CustomMemoryManager.service.impl.PersistorServiceLocalFileImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	// Set folderLocation path as a path of your choice in @UtilService class
	public void writeObjects() {

		List<Entity<Serializable>> entities = new ArrayList<Entity<Serializable>>();

		for (int i = 0; i < 3000; i++) {

			entities.add(new Entity<Serializable>("File" + i, new DummyObject("abc" + i)));

		}

		PersitorService persitorService = new PersistorServiceLocalFileImpl();
		persitorService.persistObjects(entities);

	}

	public void readObjects() {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < 1000; i++) {

			list.add("File" + i);
		}

		DeallocatorService deallocatorService = new DeallocatorServiceImpl();
		deallocatorService.evictAll(list);
		System.out.println(list);
	}

}
