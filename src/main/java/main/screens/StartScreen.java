package main.screens;

import framework.Locator;
import framework.AppObject;

public class StartScreen extends BasePage {

	@Locator(android = "id=sign_in_button",
			ios = "label=LOG IN")
	protected AppObject loginButton;

	public LoginScreen clickLoginButton() {
		loginButton.tap();
		return new LoginScreen();
	}

}
