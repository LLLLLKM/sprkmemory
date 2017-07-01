package com.memory.manager.CustomMemoryManager.service;

import java.util.List;

import com.memory.manager.CustomMemoryManager.exception.MyException;

public interface DeallocatorService {

	void evict(String name) throws MyException;

	void evictAll(List<String> names) throws MyException;

}
