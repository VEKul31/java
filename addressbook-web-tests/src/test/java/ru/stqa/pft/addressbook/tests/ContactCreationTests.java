package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("name", "last name", "1234567", "test@google.com", "test1"), true);
    app.getContactHelper().enterContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
