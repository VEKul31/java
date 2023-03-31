package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
	public void testContactPhones() {
		app.goTo().homePage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
	}

	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(),contact.getWorkPhone())
					.stream().filter((s) -> ! s.equals(""))
					.map(ContactPhoneTests::cleaned)
					.collect(Collectors.joining("\n"));
	}
	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}

}
