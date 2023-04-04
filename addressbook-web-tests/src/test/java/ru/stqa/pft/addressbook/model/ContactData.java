package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {
	@XStreamOmitField
	private int id  = Integer.MAX_VALUE;
	@Expose
	private String name;
	@Expose
	private String lastName;
	@Expose
	private String address;
	private String allEmails;
	@Expose
	private String email;
	@Expose
	private String email2;
	@Expose
	private String email3;
	private String allPhones;
	@Expose
	private String homePhone;
	@Expose
	private String mobilePhone;
	@Expose
	private String workPhone;
	private String group;

	private File photo;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
	public File getPhoto() {
		return photo;
	}

	public String getAllEmails() {
		return allEmails;
	}
	public String getEmail() {
		return email;
	}
	public String getEmail2() {
		return email2;
	}

	public String getEmail3() {
		return email3;
	}



	public String getAddress() {
		return address;
	}

	public String getAllPhones() {
		return allPhones;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
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
	public ContactData withPhoto(File photo) {
		this.photo = photo;
		return this;
	}
	public ContactData withAllEmails(String allEmails) {
		this.allEmails = allEmails;
		return this;
	}
	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}
	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}
	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}
	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}
	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (id != that.id) return false;
		if (!Objects.equals(name, that.name)) return false;
		return Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}
	@Override
	public String toString() {
		return "ContactData{" +
					"id=" + id +
					", name='" + name + '\'' +
					", lastName='" + lastName + '\'' +
					'}';
	}

}
