package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

	@BeforeMethod
	public void ensurePreconditions() {
		app.getNavigationHelper().gotoHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().gotoContactPage();
			app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
		}
	}

	@Test
	public void testContactDeletion() {
		List<ContactData> before = app.getContactHelper().getContactList();
		int index = before.size() - 1;
		app.getContactHelper().selectContact(index);
		app.getContactHelper().deleteSelectedContacts();
		app.getNavigationHelper().gotoHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), index);

		before.remove(index);
		Assert.assertEquals(before, after);

	}


}
