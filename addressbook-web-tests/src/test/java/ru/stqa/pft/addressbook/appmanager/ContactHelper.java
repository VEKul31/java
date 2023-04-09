package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

	public void fillContactForm(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getName());
		type(By.name("lastname"), contactData.getLastName());
		attach(By.name("photo"), contactData.getPhoto());
		type(By.name("address"), contactData.getAddress());
		type(By.name("email"), contactData.getEmail());
		type(By.name("email2"), contactData.getEmail2());
		type(By.name("email3"), contactData.getEmail3());
		type(By.name("home"), contactData.getHomePhone());
		type(By.name("mobile"), contactData.getMobilePhone());
		type(By.name("work"), contactData.getWorkPhone());


		if (creation) {
			if (contactData.getGroups().size() > 0) {
				Assert.assertTrue(contactData.getGroups().size() == 1);
				new Select(wd.findElement(By.name("new_group")))
							.selectByVisibleText(contactData.getGroups().iterator().next().getName());
			}
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}
	public void fillContactFormBeforeMethod(ContactData contactData){
		type(By.name("firstname"), contactData.getName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("mobile"), contactData.getMobilePhone());
		type(By.name("address"), contactData.getAddress());
		type(By.name("email"), contactData.getEmail());
		attach((By.name("photo")), contactData.getPhoto());
	}

	public void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value ='" + id +"']")).click();

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
		contactCache = null;
		returnToHomePage();
	}

	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModification(contact.getId());
		fillContactForm(contact, false);
		updateContactModification();
		contactCache = null;
		returnToHomePage();
	}


	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteSelectedContacts();
		contactCache = null;
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public void addContactIntoGroup() {
		click(By.name("add"));
	}
	private Contacts contactCache = null;

	public Contacts all() {
		if (contactCache != null) {
			return new Contacts(contactCache);
		}

		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			String lastName = element.findElement(By.xpath("./td[2]")).getText();
			String name = element.findElement(By.xpath("./td[3]")).getText();
			String address = element.findElement(By.xpath("./td[4]")).getText();
			String allEmails = element.findElement(By.xpath("./td[5]")).getText();
			String allPhones = element.findElement(By.xpath("./td[6]")).getText();
			contactCache.add(new ContactData().withId(id).withName(name).withLastName(lastName).withAddress(address)
						.withAllEmails(allEmails).withAllPhones(allPhones));
		}
		return new Contacts(contactCache);
	}

	public void homePage() {
		wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if (isElementPresent(By.cssSelector("div.msgbox"))) {
			click(By.linkText("home"));
		}

	}

	public ContactData infoFromEditForm(ContactData contact) {
		initContactModificationById(contact.getId());
		String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
		String address = wd.findElement(By.name("address")).getAttribute("value");
		String email = wd.findElement(By.name("email")).getAttribute("value");
		String email2 = wd.findElement(By.name("email2")).getAttribute("value");
		String email3 = wd.findElement(By.name("email3")).getAttribute("value");
		String home = wd.findElement(By.name("home")).getAttribute("value");
		String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
		String work = wd.findElement(By.name("work")).getAttribute("value");
		wd.navigate().back();
		return new ContactData().withId(contact.getId()).withName(firstname).withLastName(lastname).withAddress(address)
					.withEmail(email).withEmail2(email2).withEmail3(email3)
					.withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
	}

	public void initContactModificationById(int id) {
		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
		WebElement row = checkbox.findElement(By.xpath("./../.."));
		List<WebElement> cells = row.findElements(By.tagName("td"));
		cells.get(7).findElement(By.tagName("a")).click();

		//or
		//wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id)).click();
		//wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id)).click();
		//wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)).click();
	}

	public void createBeforeMethod(ContactData contact) {
		fillContactFormBeforeMethod(contact);
		enterContactCreation();
		contactCache = null;
		returnToHomePage();
	}
}
