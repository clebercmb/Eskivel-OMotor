package com.test.commons.utils;

public enum QueueNames {
	TESTCLEBERQUEUE("TESTCLEBERQUEUE");
	
	private String queueName;
	QueueNames(String queueName) {
		this.queueName = queueName;
	}
	
	public String queueName() {
		return queueName;
	}
}



