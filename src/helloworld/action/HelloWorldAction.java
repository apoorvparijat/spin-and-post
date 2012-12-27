package helloworld.action;

import helloworld.model.MessageStore;
import helloworld.model.PersonDetails;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	private MessageStore messageStore;
	private String userName;
	private static int helloCount = 0;
	private PersonDetails person;
	/*
	 * This method is executed by the controller.
	 */

	public String execute() throws Exception {
		messageStore = new MessageStore();
		return "SUCCESS";
	}


	/* setters and getters */
	public MessageStore getMessageStore(){
		return messageStore;
	}
	public void setMessageStore(MessageStore messageStore){
		this.messageStore = messageStore;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String s){
		this.userName = s;
	}
	public void setPerson(PersonDetails p ){
		this.person = p;
	}
	public PersonDetails getPerson(){
		return this.person;
	}
	public int getHelloCount(){
		return helloCount++;
	}
	public void setHelloCount(int i){
		HelloWorldAction.helloCount = i;
	}
}
