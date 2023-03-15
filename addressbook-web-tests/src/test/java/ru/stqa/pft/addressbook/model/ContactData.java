package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

	private int id  = Integer.MAX_VALUE;
	private String name;
	private String lastName;
	private String mobile;
	private String email;

	private String group;

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

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withName(String name) {
		this.name = name;
		return this;
	}

	public ContactData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ContactData withMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
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
