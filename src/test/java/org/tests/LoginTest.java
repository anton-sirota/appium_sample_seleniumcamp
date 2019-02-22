package org.tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	@Test(groups = "Regression")
	public void loginTest() {
		onStartScreen()
				.clickLoginButton()
				.loginWithUser();
	}

}
