package main.screens;

import framework.Locator;
import framework.AppObject;

public class LoginScreen extends BasePage {

	@Locator(android = "id=text_login",
			ios = "label=Username")
	protected AppObject loginInput;

	@Locator(android = "id=text_password",
			ios = "label=Password")
	protected AppObject passwordInput;

	@Locator(android = "id=btn_login",
			ios = "label=Log In")
	protected AppObject loginButton;

	public void loginWithUser() {
		loginInput.type("getNameFromConfig");
		passwordInput.type("getPassFromConfig");
		loginButton.tap();
	}

}
