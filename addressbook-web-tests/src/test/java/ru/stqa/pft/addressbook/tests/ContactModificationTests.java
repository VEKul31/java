package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase{

	@Test
	public void testContactModification() {
		app.getNavigationHelper().gotoHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().gotoContactPage();
			app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
		}
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("name", "last", "1", "test2", "tes1"), false);
		app.getContactHelper().updateContactModification();
		app.getContactHelper().returnToHomePage();
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());

	}
}
