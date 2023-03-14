package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{


	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().gotoHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().gotoContactPage();
			app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
		}
		app.getNavigationHelper().gotoHomePage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().deleteSelectedContacts();
		app.getNavigationHelper().gotoHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(before.size() - 1);
		Assert.assertEquals(before, after);

	}


}
