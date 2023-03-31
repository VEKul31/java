package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().homePage();
		if (app.contact().all().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last").withAddress("почтовый адрес")
						.withEmail("123").withEmail2("234").withEmail3("345")
						.withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
						.withGroup("test1"));
		}
	}
	@Test
	public void testContactModification() {
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
					.withId(modifiedContact.getId()).withName("name").withLastName("last").withMobilePhone("1234567").withEmail("test@google.com").withGroup(null);
		app.contact().modify(contact);
		assertThat(app.contact().getContactCount(),equalTo(before.size()));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

	}

}
