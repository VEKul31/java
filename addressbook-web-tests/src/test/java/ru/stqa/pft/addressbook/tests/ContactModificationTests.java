package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
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
		ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"name", "last", "1234567", "test@google.com", null);
		app.getContactHelper().fillContactForm(contact, false);
		app.getContactHelper().updateContactModification();
		app.getContactHelper().returnToHomePage();

		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size());

		before.remove(before.size() - 1);
		before.add(contact);

		Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);;
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);

	}
}
