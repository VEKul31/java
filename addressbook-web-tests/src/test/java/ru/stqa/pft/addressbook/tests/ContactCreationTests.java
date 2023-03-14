package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.getNavigationHelper().gotoHomePage();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getNavigationHelper().gotoContactPage();
		ContactData contact = new ContactData("name", "last", "1234567", "test@google.com", null);
		app.getContactHelper().createContact(contact);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(contact);
		Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);;
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before , after);
	}

}
