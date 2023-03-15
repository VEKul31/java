package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public void fillContactForm(ContactData contactData, boolean creation ) {
		type(By.name("firstname"), contactData.getName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("mobile"), contactData.getMobile());
		type(By.name("email"), contactData.getEmail());

		if (creation) {
			new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}


	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();
	}

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
	}

	public void deleteSelectedContacts() {
		click(By.xpath("//input[@value='Delete']"));
		wd.switchTo().alert().accept();
	}

	public void initContactModification(int id) {
		wd.findElement(By.xpath("//img[@alt='Edit']")).click();
	}

	public void updateContactModification() {
		click(By.xpath("//div[@id='content']/form/input[22]"));

	}

	public void create(ContactData contact) {
		fillContactForm(contact, true);
		enterContactCreation();
		returnToHomePage();
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModification(contact.getId());
		fillContactForm(contact, false);
		updateContactModification();
		returnToHomePage();
	}

	public void delete(int index) {
		selectContact(index);
		deleteSelectedContacts();
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteSelectedContacts();
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> list() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			String name = element.getText();
			String[] components = name.split(" ");
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			contacts.add(new ContactData().withId(id).withName(components[1]).withLastName(components[0]));
		}
		return contacts;
	}

	public Set<ContactData> all() {
		Set<ContactData> contacts = new HashSet<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			String name = element.getText();
			String[] components = name.split(" ");
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			contacts.add(new ContactData().withId(id).withName(components[1]).withLastName(components[0]));
		}
		return contacts;
	}

}
