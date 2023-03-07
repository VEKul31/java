package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupCreationTests extends TestBase {

	@Test
	public void testGroupCreation() throws Exception {
		gotoGroupPage();
		initGroupCreation();
		fillGroupForm(new GroupData("test1", "test2", "test3"));
		submitGroupCreation();
		returnToGroupPage();
	}

	private void returnToGroupPage() {
		wd.findElement(By.linkText("Logout")).click();
	}

	private void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}

	private void fillGroupForm(GroupData groupData) {
		wd.findElement(By.name("group_name")).click();
		wd.findElement(By.name("group_name")).clear();
		wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
		wd.findElement(By.name("group_header")).click();
		wd.findElement(By.name("group_header")).clear();
		wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
		wd.findElement(By.name("group_footer")).click();
		wd.findElement(By.name("group_footer")).clear();
		wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
	}

	private void initGroupCreation() {
		wd.findElement(By.name("new")).click();
	}

	private void gotoGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}

}
