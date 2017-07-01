package com.memory.manager.CustomMemoryManager.service.impl;

import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import com.memory.manager.CustomMemoryManager.dto.Entity;
import com.memory.manager.CustomMemoryManager.exception.MyException;
import com.memory.manager.CustomMemoryManager.service.PersitorService;
import com.memory.manager.CustomMemoryManager.util.UtilService;

public class PersistorServiceLocalFileImpl implements PersitorService {

	public <T extends Serializable> void persistObject(Entity<T> entity) throws MyException {
		FileOutputStream fos;
		try {

			fos = new FileOutputStream(UtilService.folderPath + entity.getName() + ".txt");
			ObjectOutput ou = new ObjectOutputStream(fos);
			ou.writeObject(entity);
			ou.close();

		} catch (Exception e) {
			throw new MyException(e.getMessage(), e);
		}

	}

	public <T extends Serializable> void persistObjects(List<Entity<T>> entities) throws MyException {

		try {

			for (Entity<T> entity : entities) {

				persistObject(entity);

			}

		} catch (Exception e) {
			throw new MyException(e.getMessage(), e);
		}

	}

}
