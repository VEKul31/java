package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@Test
	public void testContactCreationTests() {
		app.goTo().homePage();
		Contacts before = app.contact().all();
		app.goTo().contactPage();
		ContactData contact = new ContactData().withName("name").withLastName("last").withMobilePhone("1234567").withEmail("test@google.com").withGroup("test1");
		app.contact().create(contact);
		assertThat(app.contact().getContactCount(),equalTo(before.size() +1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo
					(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}

}
