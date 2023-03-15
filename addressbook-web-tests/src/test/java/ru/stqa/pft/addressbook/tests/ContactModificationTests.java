package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().homePage();
		if (app.contact().list().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last").withMobile("1234567").withEmail("test@google.com").withGroup(null));
		}
	}
	@Test
	public void testContactModification() {
		List<ContactData> before = app.contact().list();
		int index = before.size() - 1;
		ContactData contact = new ContactData()
					.withId(before.get(index).getId()).withName("name").withLastName("last").withMobile("1234567").withEmail("test@google.com").withGroup(null);
		app.contact().modify(index, contact);
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size());

		before.remove(index);
		before.add(contact);
		Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);;
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);

	}

}