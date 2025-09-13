package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	
		@FindBy(id = "input-email")
		WebElement emailIdInputBox;
		
		@FindBy(id = "input-password")
		WebElement passwordInputBox;
		
		@FindBy(xpath = "//input[@value='Login']")
		WebElement loginButton;
		
		@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
		WebElement warningMsg;
		
		/*-----------------------------------------------
					------------------------*/
		
		//Methods
		
		public void loginUser(String email, String password) {
			emailIdInputBox.sendKeys(email);
			passwordInputBox.sendKeys(password);
			loginButton.click();
		}
		
		public boolean isWarningMessageDisplayed() {
		    try {
		        return warningMsg.isDisplayed();
		    } catch (Exception e) {
		        return false;
		    }
		}

		public String getWarningMessage() {
		    try {
		        return warningMsg.getText();
		    } catch (Exception e) {
		        return "";
		    }
		}
}
