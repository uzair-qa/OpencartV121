package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	
	//Locators
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstNameInputBox;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastNameInputBox;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailInputBox;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephoneInputBox;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordInputBox;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confirmPasswordInputBox;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement privacyPolicyToggle;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement continueButton;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement accountCreationTxtMssg;
	
	/*------------------------------------------------------\
						-----------------------------------------------*/
	
	
	//methods
	
	/**
	 * Logs in the user with the provided credentials.
	 * @param fname
	 * @param lname
	 * @param email
	 * @param pass
	 */
	public void registerUser(String fname, String lname, String email, String telephone, String pass, String confPass) {
		firstNameInputBox.sendKeys(fname);
		lastNameInputBox.sendKeys(lname);
		emailInputBox.sendKeys(email);
		telephoneInputBox.sendKeys(telephone);
		passwordInputBox.sendKeys(pass);
		confirmPasswordInputBox.sendKeys(confPass);
		privacyPolicyToggle.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}
	
	/**
	 * Retrieves the confirmation message after account creation
	 * @return the confirmation text on success, or the exception message on failure
	 */
	public String getConfirmationMsg() {
		try {
			return (accountCreationTxtMssg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
