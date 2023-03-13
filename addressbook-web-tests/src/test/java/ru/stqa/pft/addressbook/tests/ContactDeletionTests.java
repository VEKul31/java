package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{


	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().gotoHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().gotoContactPage();
			app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
		}
		app.getNavigationHelper().gotoHomePage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContacts();
		app.getNavigationHelper().gotoHomePage();
	}

}
