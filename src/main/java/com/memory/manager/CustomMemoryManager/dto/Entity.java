package com.memory.manager.CustomMemoryManager.dto;

import java.io.Serializable;

public class Entity<T extends Serializable> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	public Entity(String name, T object) {
		super();
		this.name = name;
		this.object = object;
	}

	private T object;

	public Entity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}
