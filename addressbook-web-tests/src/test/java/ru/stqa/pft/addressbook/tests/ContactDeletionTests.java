package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

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
	public void testContactDeletion() {
		Contacts before = app.db().contacts();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		app.contact().homePage();
		assertThat(app.contact().getContactCount(),equalTo(before.size() -1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.withOut(deletedContact)));
		verifyContactListInUI();
	}

}
