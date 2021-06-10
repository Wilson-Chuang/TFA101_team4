package testdatasource;

import java.io.File;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class testcapture {
	
	public static void main(String[] args) throws Exception{
		
		Date currentdate = new Date();
		
		String screenshotfilename = currentdate.toString().replace(" ","-").replace(":","-");
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Tibame_T14\\Desktop\\NewChrome\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
//		String currentURL = driver.getCurrentUrl();
		
		driver.get("http://localhost:8081/Team4_MVC/product_list/Checkout5.jsp");
//		driver.get(currentURL);
		
		driver.manage().window().maximize();
		
		Thread.sleep(1000);
		
		TakesScreenshot tc=(TakesScreenshot)driver;
		
		File src=tc.getScreenshotAs(OutputType.FILE);  
		

		FileHandler.copy(src, new File("C:\\Users\\Tibame_T14\\Desktop\\screenshot\\" + screenshotfilename+".png")); 	
		driver.quit();
		
	}
}
