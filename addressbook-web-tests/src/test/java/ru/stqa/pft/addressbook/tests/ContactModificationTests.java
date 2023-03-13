package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

	@Test
	public void testContactModification() {
		app.getNavigationHelper().gotoHomePage();
		if (! app.getContactHelper().isThereAContact()) {
			app.getNavigationHelper().gotoContactPage();
			app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
		}
		app.getContactHelper().selectContact();
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("name", "last", "1", "test2", "tes1"), false);
		app.getContactHelper().updateContactModification();
		app.getContactHelper().returnToHomePage();

	}
}
