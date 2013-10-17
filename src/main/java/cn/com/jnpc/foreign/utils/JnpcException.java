package cn.com.jnpc.foreign.utils;

public class JnpcException extends Exception {
    private String returnMessage;

    private String getReturnMessage() {
	return this.returnMessage;
    }

    public JnpcException(String returnMessage) {
	this.returnMessage = returnMessage;
    }
}
