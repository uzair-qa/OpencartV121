package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager; //--> Importing Logger
import org.apache.logging.log4j.Logger;

public class BaseTest {

	public static WebDriver driver;
	public Logger logger; // Initialize logger
	public Properties properties; // For properties.config

	@BeforeClass(groups = { "sanity", "regression", "master" })
	@Parameters({ "OS", "browser" }) // -->Using Parameters from master.xml
	public void setup(String os, String browserName) throws IOException {

		// Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);

		// using Logger
		logger = LogManager.getLogger(this.getClass());

		// ---------------------------------------------------------------------------------------

		// when--> execution_env=remote
		if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// OS
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX); 
			}else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching operating system");
				return;
			}

			// Browser
			switch (browserName.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		// -----------------------------------------------------------------------------------------------------

		// When --> execution_env=local
		if (properties.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (browserName.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser Name..");
				return;
			}
		}

		// Switch browser using Switch case
//		switch (browserName) {
//		case "chrome":
//
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("disable-popup-blocking");
//			options.addArguments("start-maximized");
//			options.addArguments("--disable-notifications");
//			options.addArguments("--incognito"); // important: incognito = no password manager
//x`
//			driver = new ChromeDriver(options);
//			break;
//		case "edge":
//			driver = new EdgeDriver();
//			break;
//		case "firefox":
//			driver = new FirefoxDriver();
//			break;
//		default:
//			System.out.println("Invalid Browser Name");
//			return; // --> return to exit from entire execution
//		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// driver.get("http://localhost/opencart/upload/");
		driver.get(properties.getProperty("appURL")); // reading url from config.properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "sanity", "regression", "master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5); // commons dependency used
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10); // commons dependency used
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3); // commons dependency used
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatedNumber);
	}

	// Screenshot method
	public String captureScreen(String tName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String fileName = tName + "_" + timeStamp + ".png";

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "\\screenshots\\" + fileName;

		try {
			FileUtils.copyFile(srcFile, new File(destPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath;
	}
}
