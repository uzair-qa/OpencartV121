package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginTest extends BaseTest {

	@Test(groups = {"sanity","master"})
	public void verify_login() {
		logger.info("*********Starting TC002_LoginTest **************");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			lp.loginUser(properties.getProperty("email"), properties.getProperty("password"));		//Fetching data from properties file
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPageTitle = macc.isMyAccountPageExists();
			
			Assert.assertTrue(targetPageTitle); 	//Expecting targetPageTitle to be true
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*********Finished TC002_LoginTest **************");
		
	}
}
