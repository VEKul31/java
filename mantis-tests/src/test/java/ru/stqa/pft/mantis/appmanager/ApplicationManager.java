package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.mantis.tests.RegistrationTests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	private final Properties properties;
	private WebDriver wd;

	private String browser;
	private RegistrationHelper registrationHelper;
	private FtpHelper ftp;
	private MailHelper mailHelper;
	private JamesHelper jamesHelper;
	private AdministratorHelper administratorHelper;
	private UserHelper userHelper;
	private SoapHelper soapHelper;

	private RestHelper restHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
	}

	public void stop() {
		if (wd != null) {
			wd.quit();
		}
	}

	public HttpSession newSession() {
		return new HttpSession(this);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public RegistrationHelper registration() {
		if (registrationHelper == null) {
			registrationHelper = new RegistrationHelper(this);
		}
		return registrationHelper;
	}

	public FtpHelper ftp() {
		if (ftp == null) {
			ftp = new FtpHelper(this);
		}
		return ftp;
	}

	public WebDriver getDriver() {
		if (wd == null) {
			if (browser.equals(BrowserType.FIREFOX)) {
				wd = new FirefoxDriver();
			} else if (browser.equals(BrowserType.CHROME)) {
				wd = new ChromeDriver();
			} else if (browser.equals(BrowserType.EDGE)){
				System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
				wd = new EdgeDriver();
			}

			wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wd.get(properties.getProperty("web.baseUrl"));
		}
		return wd;
	}

	public MailHelper mail() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}

	public AdministratorHelper administrator() {
		if (administratorHelper == null) {
			administratorHelper = new AdministratorHelper(this);
		}
		return administratorHelper;
	}

	public UserHelper user() {
		if (userHelper == null) {
			userHelper = new UserHelper(this);
		}
		return userHelper;
	}

	public SoapHelper soap() {
		if (soapHelper == null) {
			soapHelper = new SoapHelper(this);
		}
		return soapHelper;
	}

	public RestHelper rest() {
		if (restHelper == null){
			restHelper = new RestHelper(this);
		}
		return restHelper;
	}

	public JamesHelper jamesMailAgent() {
		if (jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}
}
