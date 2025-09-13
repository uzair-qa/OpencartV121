package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseTest {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) {

		logger.info("*********Starting TC003_LoginDD **************");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.loginUser(email, pwd); // Fetching data from properties file

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPageTitle = macc.isMyAccountPageExists(); // true--> login success	 false--> login failed

			/*
			 * Data is valid - login success - test pass - logout 
			 * 				 - login failed - test fail
			 * 
			 * 
			 * Data is invalid - login success - test fail - logout 
			 * 				   - login fail - test pass
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPageTitle == true) {
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPageTitle == true) {
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();  // Print root cause in console
		    Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		logger.info("*********Finished TC003_LoginDD **************");
	}
}
