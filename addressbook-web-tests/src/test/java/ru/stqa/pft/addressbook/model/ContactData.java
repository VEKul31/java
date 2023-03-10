package ru.stqa.pft.addressbook.model;

public class ContactData {
	private final String name;
	private final String last_name;
	private final String mobile;
	private final String email;
	private String group;

	public ContactData(String name, String last_name, String mobile, String email, String group) {
		this.name = name;
		this.last_name = last_name;
		this.mobile = mobile;
		this.email = email;
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getGroup() {
		return group;
	}
}
