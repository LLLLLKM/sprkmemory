package com.memory.manager.CustomMemoryManager.service;

import java.io.Serializable;
import java.util.List;

import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.exception.MyException;

public interface FetcherService {

	<T extends Serializable> Entity<T> get(String name) throws MyException;

	<T extends Serializable> List<Entity<T>> getAll(List<String> names) throws MyException;

}
