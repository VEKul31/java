package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getNavigationHelper().gotoHomePage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getNavigationHelper().gotoContactPage();
		app.getContactHelper().createContact(new ContactData("name", "last name", "1234567", "test@google.com", null));
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);
	}

}
