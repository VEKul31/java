package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class AdministratorHelper extends HelperBase{


	public AdministratorHelper(ApplicationManager app) {
		super(app);
	}
	public void loginByAdmin() {
		wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
		type(By.name("username"), app.getProperty("web.adminLogin"));
		type(By.name("password"), app.getProperty("web.adminPassword"));
		click(By.cssSelector("input[value='Login']"));
	}

	public void goToManageUserPage() {
		click(By.linkText("Manage Users"));
	}

	public Users allUsers() {
		Users users = new Users();
		List<WebElement> elements = wd.findElements(By.className("row-1"));
		List<WebElement> elements2 = wd.findElements(By.className("row-2"));
		elements.addAll(elements2);
		for (WebElement element : elements) {
			List<WebElement> cells = element.findElements(By.tagName("td"));
			String userName = cells.get(0).findElement(By.tagName("a")).getText();
			if (userName.equals(app.getProperty("web.adminLogin"))) {
				continue;
			}
			String email = cells.get(2).getText();
			User user = new User().withUserName(userName).withUserEmail(email);
			users.add(user);
		}
		return users;
	}

	public void changePassword(User user) {
		click(By.linkText(user.getUserName()));
		click(By.cssSelector("input[value='Reset Password']"));
	}

}