package org.archstudio.archipelago.core.util;

public class PropertyDecodeException extends Exception{

	public PropertyDecodeException(){
	}

	public PropertyDecodeException(String message){
		super(message);
	}

	public PropertyDecodeException(Throwable cause){
		super(cause);
	}

	public PropertyDecodeException(String message, Throwable cause){
		super(message, cause);
	}

}
