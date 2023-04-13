package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.db().contacts().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last").withAddress("почтовый адрес")
						.withEmail("123").withEmail2("234").withEmail3("345")
						.withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
		}
	}
	@Test
	public void testContactModification() {
		Contacts before = app.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData()
					.withId(modifiedContact.getId()).withName("name1").withLastName("last1").withAddress("почтовый адрес1")
					.withEmail("123").withEmail2("234").withEmail3("345")
					.withHomePhone("111").withMobilePhone("222").withWorkPhone("333");
		File photo = new File("src/test/resources/stru.png");
		contact.withPhoto(photo);
		app.goTo().homePage();
		app.contact().modify(contact);
		Contacts after = app.db().contacts();
		assertThat(app.contact().getContactCount(),equalTo(before.size()));
		assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
		verifyContactListInUI();
	}
}
