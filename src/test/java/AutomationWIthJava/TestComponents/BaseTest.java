package AutomationWIthJava.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fir = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\Resources\\GlobalData.properties");
		prop.load(fir);
		String browser = prop.getProperty("browser");
		if (browser.contains("chrome")) {

			driver = new ChromeDriver();
		} else if (browser.contains("Edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage lunchApplication() throws IOException {
		driver=initializeDriver();
		 loginPage=new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		File file= new File(filePath);
		String jsonContent=FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String,String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
		
	}
	
	
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	
	
}
