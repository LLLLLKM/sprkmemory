package com.memory.manager.CustomMemoryManager.service;

import java.io.Serializable;
import java.util.List;

import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.exception.MyException;

public interface PersitorService {

	public <T extends Serializable> void persistObject(Entity<T> entity) throws MyException;

	public <T extends Serializable> void persistObjects(List<Entity<T>> entities) throws MyException;
}
