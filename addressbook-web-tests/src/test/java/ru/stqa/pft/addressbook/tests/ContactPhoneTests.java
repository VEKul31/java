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
		if (app.db().contacts().size() == 0) {
			app.goTo().contactPage();
			app.contact().create(new ContactData().withName("name").withLastName("last").withAddress("почтовый адрес")
						.withEmail("123").withEmail2("234").withEmail3("345")
						.withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
		}
	}

	@Test
	public void testContactPhones() {
		app.goTo().homePage();
		ContactData contact = app.contact().all().iterator().next();
		ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

		assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
		assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
		assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
		verifyContactListInUI();
	}

	private String mergePhones(ContactData contact) {
		return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(),contact.getWorkPhone())
					.stream().filter((s) -> ! s.equals(""))
					.map(ContactPhoneTests::cleaned)
					.collect(Collectors.joining("\n"));
	}

	private String mergeEmails(ContactData contact) {
		return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
					.stream().filter((s) -> ! s.equals(""))
					.collect(Collectors.joining("\n"));
	}
	public static String cleaned(String phone) {
		return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
	}

}
