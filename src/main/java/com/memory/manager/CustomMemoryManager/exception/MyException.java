package com.memory.manager.CustomMemoryManager.exception;

public class MyException extends Exception{

	String meesage;

	Exception exception;

	public MyException(String meesage, Exception exception) {
		super();
		this.meesage = meesage;
		this.exception = exception;
	}

}
