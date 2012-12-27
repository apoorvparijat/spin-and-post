
package com.example.actions;

import com.opensymphony.xwork2.ActionSupport; 

public class HeyJude extends ActionSupport {
  private String message;
	public String getMessage() {
        	return message;
	}
	public String execute() {
		message = "Hello World!";
		return SUCCESS;
	}
}
