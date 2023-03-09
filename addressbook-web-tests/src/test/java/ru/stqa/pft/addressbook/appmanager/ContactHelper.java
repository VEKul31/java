package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void returnToHomePage() {
		click(By.linkText("home page"));
	}

	public void enterContactCreation() {
		click(By.xpath("//div[@id='content']/form/input[21]"));
	}

	public void fillContactForm(ContactData contactData) {
		type(By.name("firstname"), contactData.getName());
		type(By.name("lastname"), contactData.getLast_name());
		type(By.name("mobile"), contactData.getMobile());
		type(By.name("email"), contactData.getEmail());
	}

}
