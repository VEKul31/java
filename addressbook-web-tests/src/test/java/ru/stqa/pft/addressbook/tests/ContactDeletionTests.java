package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{


	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().gotoHomePage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContacts();
	}

}
