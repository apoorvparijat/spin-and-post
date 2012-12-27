
package com.example.actions;

import com.opensymphony.xwork2.ActionSupport; 
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

	@Result(name="success",location="hey-jude.jsp")
public class HeyJudeAction extends ActionSupport {
  private String message;
	public String getMessage() {
        	return message;
	}
	@Action("/apoorv")
	public String execute() {
		message = "Hello World!";
		return SUCCESS;
	}
}
