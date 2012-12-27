package helloworld.model;
import org.apache.log4j.Logger;
public class PersonDetails{
	
	private String userName;
	private String email;
	private String info;
	private static Logger logger = Logger.getLogger("personDetails");
	/* setters and getters */

	public void setInfo(){
		info = userName + " @ " + email;
		logger.debug("SetInfo called : " +info);
	}
	public void setUserName(String s){
		this.userName = s;
		logger.warn("SetUserName called : " + userName);
	}

	public void setEmail(String s){
		this.email = s;
		logger.debug("SetEmail called : " + email);
	}
	
	public String getInfo(){
		return userName + " : " + email;
	}

	public String getUserName(){
		return this.userName;
	}

	public String getEmail(){
		return this.email;
	}
}
