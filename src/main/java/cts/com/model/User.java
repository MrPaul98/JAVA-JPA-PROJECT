package cts.com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class User {
	
	@Id
	private String Id;
	private String Password;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

}
