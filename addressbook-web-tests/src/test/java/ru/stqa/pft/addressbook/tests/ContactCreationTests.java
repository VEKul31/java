package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.gotoContactPage();
    app.fillContactForm(new ContactData("name", "last name", "1234567", "test@google.com"));
    app.enterContactCreation();
    app.returnToHomePage();
  }

}
