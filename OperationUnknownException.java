package org.foo.app;

public class OperationUnknownException extends Exception {
	public OperationUnknownException(String OperationName){
		System.out.println("operation name is "+OperationName+" executed");
	}
}