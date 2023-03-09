package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    gotoContactPage();
    fillContactForm(new ContactData("name", "last name", "1234567", "test@google.com"));
    enterContactCreation();
    returnToHomePage();
  }

}
