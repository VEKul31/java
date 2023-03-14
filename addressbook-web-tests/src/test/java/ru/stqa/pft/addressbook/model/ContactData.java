package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

	private int id;
	private final String name;
	private final String lastName;
	private final String mobile;
	private final String email;

	private String group;


	public ContactData(String name, String lastName, String mobile, String email, String group) {
		this.id = Integer.MAX_VALUE;
		this.name = name;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.group = group;
	}

	public ContactData(int id, String name, String lastName, String mobile, String email, String group) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
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

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ContactData{" +
					"id=" + id +
					", name='" + name + '\'' +
					", lastName='" + lastName + '\'' +
					'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (!Objects.equals(name, that.name)) return false;
		return Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}
}
