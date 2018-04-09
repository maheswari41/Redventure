package frontier;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Frontier_Chrome {
	WebDriver driver;

	@BeforeClass
	// to launch Frontier
	public void homePage() {
		System.setProperty("webdriver.chrome.driver", "C:\\Automation_Tools\\Selenium\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();// to launch Chrome
		driver.manage().window().maximize();// maximize window
		driver.manage().deleteAllCookies();// delete all the cookies
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);// dynamic wait
		driver.get("https://internet.frontier.com/");// to get URL
	}

	@Test(priority = 1, groups = "Title")
	// to verify home page of the title
	public void verifyHomepageTitle() {
		String Expected = "Frontier® Internet Service | 855-858-4802 | Frontier Communications";
		String title = driver.getTitle();
		System.out.println("The title of the page is  " + title);
		Assert.assertEquals(title, Expected);
	}

	@Test(priority = 2, groups = "Logo")
	// to verify the Logo
	public void frontierLogoTest() {
		boolean b = driver.findElement(By.xpath("//*[@id='js-track-logo']")).isDisplayed();
		if (b == true) {
			System.out.println("The Logo is present");
		}
	}

	@Test(priority = 3, groups = "Links")
	// to get the list of all the links
	public void links() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Size of full links----->  " + links.size());
		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i).getText());
		}
	}

	@Test(priority = 4)
	// mouseOver to see the options list
	public void mouseHover() throws InterruptedException {
		WebElement ele = driver.findElement(By.linkText("Plans & Pricing"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		Thread.sleep(2000);
		WebElement ele1 = driver.findElement(By.linkText("Internet"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(ele1).perform();
		Thread.sleep(2000);
		WebElement ele2 = driver.findElement(By.linkText("TV"));
		Actions act2 = new Actions(driver);
		act2.moveToElement(ele2).perform();
	}

	@Test(priority = 5)
	// to get the background color of shop online link
	public void backgColor() {
		WebElement link_color = driver.findElement(By.linkText("Shop Online"));
		String bgColor = link_color.getCssValue("background-color");
		System.out.println("Before Hover :: " + bgColor);
		Actions builder = new Actions(driver);
		builder.moveToElement(link_color).build().perform();
		bgColor = link_color.getCssValue("background-color");
		System.out.println("After Hover :: " + bgColor);
	}

	@AfterClass
	// to close the Browser
	public void terminateBrowser() {
		driver.quit();
	}

}
