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
		if (app.db().contacts().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last").withAddress("почтовый адрес")
						.withEmail("123").withEmail2("234").withEmail3("345")
						.withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
						.withGroup("test1"));
		}
	}
	@Test
	public void testContactModification() {
		Contacts before = app.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
					.withId(modifiedContact.getId()).withName("name1").withLastName("last1").withAddress("почтовый адрес1")
					.withEmail("123").withEmail2("234").withEmail3("345")
					.withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
					.withGroup("test1");
		app.goTo().homePage();
		app.contact().modify(contact);
		assertThat(app.contact().getContactCount(),equalTo(before.size()));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

	}

}
