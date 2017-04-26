package model;

public class User {
private String username;
private String password;
private String nikename;
private String mail;

public User(String username, String password, String nikename, String mail) {
	super();
	this.username = username;
	this.password = password;
	this.nikename = nikename;
	this.mail = mail;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNikename() {
	return nikename;
}
public void setNikename(String nikename) {
	this.nikename = nikename;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}


}
