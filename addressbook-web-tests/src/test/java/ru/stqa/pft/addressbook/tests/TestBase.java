package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@Listeners(MyTestListener.class)
public class TestBase {
	Logger logger = LoggerFactory.getLogger(TestBase.class);
	protected static final ApplicationManager app
				= new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

	@BeforeSuite(alwaysRun = true)
	public void setUp(ITestContext context) throws Exception {
		app.init();
		context.setAttribute("app", app);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();
	}

	@BeforeMethod
	public void logTestStart(Method m, Object [] p){
		logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
	}

	@AfterMethod(alwaysRun = true)
	public void logTestStop(Method m){
		logger.info("Stop test " + m.getName());
	}

	public void verifyGroupListInUI() {
		if (Boolean.getBoolean("verifyUI")) {
			Groups dbGroups = app.db().groups();
			Groups uiGroups = app.group().all();
			assertThat(uiGroups, equalTo(dbGroups.stream()
						.map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
						.collect(Collectors.toSet())));
		}
	}

	public void verifyContactListInUI() {
		if (Boolean.getBoolean("verifyUIContacts")) {
			Contacts dbContacts = app.db().contacts();
			Contacts uiContacts = app.contact().all();
			assertThat(uiContacts, equalTo(dbContacts.stream().map((c) -> new ContactData().withId(c.getId()).withName(c.getName()))
						.collect(Collectors.toSet())));
		}
	}
}