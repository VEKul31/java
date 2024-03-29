package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContactsFromCsv() {
		File photo = new File("src/test/resources/stru.png");
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] {new ContactData().withName("name").withLastName("last").withPhoto(photo).withMobilePhone("123").withEmail("test@google.com")});
		list.add(new Object[] {new ContactData().withName("name2").withLastName("last2").withPhoto(photo).withMobilePhone("1234").withEmail("tes@google.com")});
		list.add(new Object[] {new ContactData().withName("name3").withLastName("last3").withPhoto(photo).withMobilePhone("12345").withEmail("te@google.com")});
		return list.iterator();
	}
	@DataProvider
	public Iterator<Object[]> validContactsFromXml() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
			String xml = "";
			String line = reader.readLine();
			while (line != null) {
				xml += line;
				line = reader.readLine();
			}
			XStream xStream = new XStream();
			xStream.processAnnotations(ContactData.class);
			xStream.allowTypes(new Class[]{ContactData.class});
			List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
			return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
		}
	}

	@DataProvider
	public Iterator<Object[]> validContactsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
			return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
		}
	}

	@Test(dataProvider = "validContactsFromJson")
	public void testContactCreation(ContactData contact) {
		Contacts before = app.db().contacts();
		File photo = new File("src/test/resources/stru.png");
		contact.withPhoto(photo);
		app.goTo().contactPage();
		app.contact().create(contact);
		assertThat(app.contact().getContactCount(),equalTo(before.size() +1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo
					(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
		verifyContactListInUI();
	}

}
