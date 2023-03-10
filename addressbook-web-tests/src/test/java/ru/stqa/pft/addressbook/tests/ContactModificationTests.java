package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

	@Test
	public void testContactModification() {
		app.getNavigationHelper().gotoHomePage();
		app.getContactHelper().selectContact();
		app.getContactHelper().initContactModification();
		app.getContactHelper().fillContactForm(new ContactData("name", "last name", "1234567", "test@google.com", null), false);
		app.getContactHelper().updateContactModification();
		app.getContactHelper().returnToHomePage();

	}
}
