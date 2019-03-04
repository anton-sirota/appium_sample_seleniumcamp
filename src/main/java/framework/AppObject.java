package framework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class AppObject {
	private String locator;
	private String controlName;
	private AppiumDriver driver;

	/**
	 * Constructs an AbstractElement with locator.
	 *
	 * @param locator
	 */
	public AppObject(AppiumDriver driver, String locator) {
		this.locator = locator;
		this.driver = driver;
	}

	/**
	 * Constructs an AbstractElement with locator and controlName.
	 *
	 * @param locator     the element locator
	 * @param controlName the control name used for logging
	 */
	public AppObject(AppiumDriver driver, String locator, String controlName) {
		this.locator = locator;
		this.controlName = controlName;
		this.driver = driver;
	}

	/**
	 * Instance method used to call static class method locateElement.
	 *
	 * @return the web element found by locator
	 */
	public MobileElement getElement() {
		MobileElement foundElement = null;
		try {
			foundElement = locateElement(locator);
		} catch (NoSuchElementException p) {
			//throw Exception
		}
		return foundElement;
	}

	/**
	 * Instance method used to call static class method locateElements.
	 *
	 * @return the list of web elements found by locator
	 */
	public List<MobileElement> getElements() {
		List<MobileElement> foundElements = null;
		try {
			foundElements = locateElements();
		} catch (NoSuchElementException n) {
			//throw Exception
		}

		return foundElements;
	}

	public String getLocator() {
		return locator;
	}

	public String getText() {
		return getElement().getText();
	}

	public void clickOnElementWithText(String text) {
		locateElement(String.format(locator, text)).click();
	}

	/**
	 * Alternative for default click
	 */
	public void actionClick() {
		getElement().click();
	}

	public void tap() {
		MobileElement element = getElement();
		PointOption pointOption = PointOption.point(element.getCenter().x, element.getCenter().y);
		new TouchAction(driver).tap(pointOption).perform();
	}

	public void type(String value) {
		getElement().setValue(value);
	}

	public void cleanAndType(String value) {
		clear();
		type(value);
	}

	public void clear() {
		getElement().clear();
	}

	private MobileElement locateElement(String locator) {
		// Depended on locator type, use proper By (ex. if starts with xpath=, use driver.findElement(By.xpath(locator)), etc.)
		return null;
	}


	private List<MobileElement> locateElements() {
		// Depended on locator type, use proper By (ex. if starts with xpath=, use driver.findElement(By.xpath(locator)), etc.)
		return null;
	}

}
