package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

	WebDriver wd;

	private SessionHelper sessionHelper;
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;

	public void init() {
		wd = new FirefoxDriver(new FirefoxOptions().setBinary("D:\\Program Files (x86)\\firefox.exe"));
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("https://localhost/addressbook/");
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		contactHelper = new ContactHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

	public GroupHelper getGroupHelper() {
		return groupHelper;
	}

	public ContactHelper getContactHelper() {
		return contactHelper;
	}

	public NavigationHelper getNavigationHelper() {
		return navigationHelper;
	}
}
