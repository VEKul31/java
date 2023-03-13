package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getNavigationHelper().gotoContactPage();
		app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
	}

}
