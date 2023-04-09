package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;


public class AddContactIntoGroup extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().homePage();
		if (app.contact().all().size() == 0) {

			app.goTo().contactPage();
			File photo = new File("src/test/resources/stru.png");
			app.contact().createBeforeMethod(
						new ContactData().withName("name").withLastName("last").withAddress("почтовый адрес")
									.withEmail("123").withEmail2("234").withEmail3("345")
									.withHomePhone("111").withMobilePhone("222").withWorkPhone("333"));
			app.goTo().homePage();
		}
	}

	@Test
	public void testAddContactIntoGroup() {
		if(app.db().groups().size() == 0) {
			app.goTo().groupPage();
			app.group().create(new GroupData().withName("test1"));
		}
		app.goTo().homePage();
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		List<WebElement> groupsList = app.group().groupsOnAddressbookPage();
		Groups groupsContact = app.db().groupsForContacts(modifiedContact.getId());
		List<Integer> groupsIdList = groupsList.stream().mapToInt(g-> Integer.parseInt(g.getAttribute("value")))
					.boxed().collect(Collectors.toList());
		List<Integer> groupsIdContact = groupsContact.stream().mapToInt(g-> g.getId())
					.boxed().collect(Collectors.toList());
		if (groupsIdContact.equals(groupsIdList)) {
			app.goTo().groupPage();
			app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
		}
		app.goTo().homePage();
		List<WebElement> groupsListAfterAdd = app.group().groupsOnAddressbookPage();
		List<Integer> groupsIdListAfterAdd = groupsListAfterAdd.stream().mapToInt(g-> Integer.parseInt(g.getAttribute("value")))
					.boxed().collect(Collectors.toList());
		List<Integer> others = groupsIdListAfterAdd.stream()
					.filter(id->!groupsIdContact.contains(id))
					.collect(Collectors.toList());
		int otherIdGroup = others.iterator().next();
		app.contact().selectContactById(modifiedContact.getId());
		app.group().chooseGroup(otherIdGroup);
		app.contact().addContactIntoGroup();
		Groups groups = app.db().groupsForContacts(modifiedContact.getId());
		List<Integer> groupsIds = groups.stream().mapToInt(g->g.getId()).boxed().collect(Collectors.toList());
		Assert.assertTrue(groupsIds.contains(otherIdGroup));

	}
}
