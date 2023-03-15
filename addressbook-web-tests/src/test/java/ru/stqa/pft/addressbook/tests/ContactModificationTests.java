package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().homePage();
		if (app.contact().all().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last").withMobile("1234567").withEmail("test@google.com").withGroup(null));
		}
	}
	@Test
	public void testContactModification() {
		Set<ContactData> before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
					.withId(modifiedContact.getId()).withName("name").withLastName("last").withMobile("1234567").withEmail("test@google.com").withGroup(null);
		app.contact().modify(contact);
		Set<ContactData> after = app.contact().all();
		Assert.assertEquals(after.size(), before.size());

		before.remove(modifiedContact);
		before.add(contact);
		Assert.assertEquals(before, after);

	}

}
