package main.screens;

import framework.PlatformType;
import framework.Settings;
import framework.Locator;
import framework.AppObject;

public class BasePage extends BasicPage {

	@Locator(android = "id=permission_allow_button")
	protected AppObject permissionAllowButton;

	/**
	 * Example of action, required only for Android
	 */
	public void allowPermissionsIfRequired() {
		if (Settings.platform.equals(PlatformType.ANDROID)) {
			permissionAllowButton.tap();
		}
	}

}