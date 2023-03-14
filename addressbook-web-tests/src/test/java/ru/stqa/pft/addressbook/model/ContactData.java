package ru.stqa.pft.addressbook.model;

import java.util.Objects;

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

	@Override
	public String toString() {
		return "ContactData{" +
					"name='" + name + '\'' +
					'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (!Objects.equals(name, that.name)) return false;
		return Objects.equals(last_name, that.last_name);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
		return result;
	}
}
