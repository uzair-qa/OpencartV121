package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseTest;

public class TC001_AccountRegisterationTest extends BaseTest {

	@Test(groups = {"regression","master"})
	public void verify_account_registeration() {
		
		logger.info("*********Starting TC001_AccountRegisterationTest **************");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link");
			
			hp.clickRegister();
			logger.info("Clicked on Register Link");

			RegisterPage rp = new RegisterPage(driver);
			
			logger.info("Providing Customer Details...");
			
			String randomPassword = randomAlphaNumeric();
			
			rp.registerUser(randomString().toUpperCase(), randomString().toUpperCase(), randomString()+"@gmail.comm", randomNumber(),randomPassword, randomPassword);
			String confirmationMssg = rp.getConfirmationMsg();
			
			if(confirmationMssg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test Case Failed...");
				logger.debug("Debug Logs...");
				Assert.assertTrue(false);
			}
			
			//Assert.assertEquals(confirmationMssg, "Your Account Has Been Created!!"); -->using if-else so that execution don't stop if assertion fails
		} catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("*********Starting TC001_AccountRegisterationTest **************");
	}
	
}
