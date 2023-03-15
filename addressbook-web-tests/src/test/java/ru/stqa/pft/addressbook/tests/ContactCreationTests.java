package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.goTo().homePage();
		List<ContactData> before = app.contact().list();
		app.goTo().contactPage();
		ContactData contact = new ContactData().withName("name").withLastName("last").withMobile("1234567").withEmail("test@google.com").withGroup("test1");
		app.contact().create(contact);
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size() + 1);

		before.add(contact);
		Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);;
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before , after);
	}

}
