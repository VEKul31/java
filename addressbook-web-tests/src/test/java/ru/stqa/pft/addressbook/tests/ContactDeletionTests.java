package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().homePage();
		if (app.contact().all().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last name").withMobile("1234567").withEmail("test@google.com").withGroup(null));
		}
	}

	@Test
	public void testContactDeletion() {
		Contacts before = app.contact().all();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.contact().homePage();
		assertThat(app.contact().getContactCount(),equalTo(before.size() -1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.withOut(deletedContact)));
	}

}
