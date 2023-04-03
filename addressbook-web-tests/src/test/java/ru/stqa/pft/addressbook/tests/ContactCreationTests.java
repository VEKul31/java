package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContacts() {
		File photo = new File("src/test/resources/stru.png");
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] {new ContactData().withName("name").withLastName("last").withPhoto(photo).withMobilePhone("123").withEmail("test@google.com").withGroup("test1")});
		list.add(new Object[] {new ContactData().withName("name2").withLastName("last2").withPhoto(photo).withMobilePhone("1234").withEmail("tes@google.com").withGroup("test2")});
		list.add(new Object[] {new ContactData().withName("name3").withLastName("last3").withPhoto(photo).withMobilePhone("12345").withEmail("te@google.com").withGroup("test3")});
		return list.iterator();
	}

	@Test(dataProvider = "validContacts")
	public void testContactCreationTests(ContactData contact) {
		app.goTo().homePage();
		Contacts before = app.contact().all();
		app.goTo().contactPage();
		app.contact().create(contact);
		assertThat(app.contact().getContactCount(),equalTo(before.size() +1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo
					(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
	}

}
