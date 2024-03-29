package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {
	protected static final ApplicationManager app
				= new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		app.init();
		app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		app.ftp().restore("config_inc.php.bak", "config_inc.php");
		app.stop();
	}

	public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
		return !app.soap().getIssueStatus(issueId).equals("fixed");
	}

	public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
		if (isIssueOpen(issueId)) {
			throw new SkipException("Ignored because of issue " + issueId);
		}
	}

	public void skipIfNotFixedBugify(int issueId) throws IOException, ServiceException {
		if (isIssueOpenBugify(issueId)) {
			throw new SkipException("Ignored because of issue " + issueId);
		}
	}

	private boolean isIssueOpenBugify(int issueId) throws IOException, ServiceException {
		return app.rest().isIssueOpen(issueId);
	}

}