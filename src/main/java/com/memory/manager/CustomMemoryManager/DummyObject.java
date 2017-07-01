package com.memory.manager.CustomMemoryManager;

import java.io.Serializable;

public class DummyObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;

	public DummyObject(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "DummyObject [name=" + name + "]";
	}
	
}
