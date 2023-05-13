package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class AdministratorPasswordTests extends TestBase {

	@BeforeMethod
	public void startMailServer() {
		app.mail().start();
	}

	@Test
	public void testChangeUserPasswordByAdmin() throws IOException, MessagingException {
		String newPassword = "123abc";
		app.administrator().loginByAdmin();
		app.administrator().goToManageUserPage();
		User user = app.administrator().allUsers().iterator().next().withNewUserPassword(newPassword);
		app.administrator().changePassword(user);
		List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
		//List<MailMessage>mailMessages = app.jamesMailAgent().waitForMail(user, newPassword, 60000);
		String confirmationLink = findConfirmationLink(mailMessages, user.getUserEmail());
		String selectedUser = app.user().changePasswordByUser(confirmationLink, user);
		assertTrue(app.newSession().login(user.getUserName(), newPassword));
	}

	private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
		MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
		VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
		return regex.getText(mailMessage.text);
	}

	@AfterMethod(alwaysRun = true)
	public void stopMailServer() {
		app.mail().stop();
	}
}
