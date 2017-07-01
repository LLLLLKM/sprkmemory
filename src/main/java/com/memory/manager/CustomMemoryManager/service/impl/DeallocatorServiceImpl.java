package com.memory.manager.CustomMemoryManager.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.memory.manager.CustomMemoryManager.exception.MyException;
import com.memory.manager.CustomMemoryManager.service.DeallocatorService;
import com.memory.manager.CustomMemoryManager.util.UtilService;

public class DeallocatorServiceImpl implements DeallocatorService {

	public void evict(String name) throws MyException{
		try {
			Files.deleteIfExists(Paths.get(UtilService.folderPath + name + ".txt"));
		} catch (Exception e) {
			throw new MyException(e.getMessage(), e);
		}
	}

	public void evictAll(List<String> names) throws MyException {
		try {

			for (String name : names) {
				evict(name);
			}

		} catch (Exception e) {
			throw new MyException(e.getMessage(), e);
		}
	}

}
