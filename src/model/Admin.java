package model;

public class Admin {
	private String id;
	private String password;
	private String name;
	private String mail;

	protected String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id=id;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password=password;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name=name;
	}

	protected String getMail() {
		return mail;
	}

	protected void setMail(String mail) {
		this.mail=mail;
	}
}
