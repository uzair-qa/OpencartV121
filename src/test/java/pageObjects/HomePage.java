package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//using Super to call parent class constructor (inheritance)
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccountElement;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerButtonElement;
	
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginButtonElement;
	
	/*-----------------------------------------------
				------------------------*/
	
	//Methods
	
	public void clickMyAccount() {
		myAccountElement.click();
	}
	
	public void clickRegister() {
		registerButtonElement.click();
	}
	
	public void clickLogin() {
		loginButtonElement.click();
	}
	
}
