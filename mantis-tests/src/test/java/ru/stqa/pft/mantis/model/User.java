package ru.stqa.pft.mantis.model;

public class User {

	private String userName;
	private String userEmail;
	private String newUserPassword;

	public String getUserName() {
		return userName;
	}

	public User withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public User withUserEmail(String userEmail) {
		this.userEmail = userEmail;
		return this;
	}

	public String getNewUserPassword() {
		return newUserPassword;
	}

	public User withNewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
		return userEmail != null ? userEmail.equals(user.userEmail) : user.userEmail == null;
	}

	@Override
	public int hashCode() {
		int result = userName != null ? userName.hashCode() : 0;
		result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
					"userName='" + userName + '\'' +
					", userEmail='" + userEmail + '\'' +
					", newUserPassword='" + newUserPassword + '\'' +
					'}';
	}
}