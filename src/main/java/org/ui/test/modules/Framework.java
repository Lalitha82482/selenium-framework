package org.ui.test.modules;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Framework {

	private WebDriver driver;
	public ExecutionReport report;
	private DataReader dataReader;
	public ExtentTest testReport;

	public Framework()
	{
		this.report = new ExecutionReport();
		this.dataReader = new DataReader();
	}

	public void browser()
	{
		String browserValue= dataReader.readConfigValue("Browser");
		try {
			switch (browserValue) {
			case "chrome":
				driver = new ChromeDriver();
				testReport.log(Status.INFO, "Opened Chrome Browser");
				takescreenShot("chrome");
				break;
			case "ie":
				driver = new InternetExplorerDriver();
				testReport.log(Status.INFO, "Opened IE Browser");
				takescreenShot("ie");
				break;
			case "firefox":
				driver = new FirefoxDriver();
				testReport.log(Status.INFO, "Opened FireFox Browser");
				takescreenShot("firefox");
				break;
			default:
				System.out.println("Invalid Selection of Browser Value");
				testReport.log(Status.FATAL, "Invalid Selection of Browser Value");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR,"Error in browser method:- "+ e.getMessage());
			takescreenShot("browser");
		}
	}

	public void application()
	{
		try {
			String url= dataReader.readConfigValue("url");
			driver.get(url);
			testReport.log(Status.INFO, "Opened Application URL :- "+url);
			takescreenShot("app");
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in application method :- "+e.getMessage());
			takescreenShot("app");
		}
	}

	public void typeIn(String fieldName)
	{
		try {
			locator(fieldName).sendKeys(dataReader.readDataValue(fieldName));
			testReport.log(Status.INFO, "User entered value : "+dataReader.readDataValue(fieldName)+" in "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in typeIn method :- "+e.getMessage());		
			takescreenShot(fieldName);
		}
	}
	public void clickIn(String fieldName)
	{
		try {
			locator(fieldName).click();
			testReport.log(Status.INFO, "User Clicked  in "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in click method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void clearAll(String fieldName)
	{
		try {
			locator(fieldName).clear();
			testReport.log(Status.INFO, "User Cleared value in "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in clearAll method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}

	public void selectDropdownByValue(String fieldName)
	{
		try {
			WebElement selectValue= locator(fieldName);
			Select dropdown = new Select(selectValue);
			dropdown.selectByValue(dataReader.readDataValue(fieldName));
			testReport.log(Status.INFO, "User Selected value : "+dataReader.readDataValue(fieldName)+"  in "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in selectDropdownByValue method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void selectDropdownByIndex(String fieldName)
	{
		try {
			Select dropdown = new Select(locator(fieldName));
			dropdown.selectByIndex(Integer.parseInt(dataReader.readDataValue(fieldName)));
			testReport.log(Status.INFO, "User Selected value : "+dataReader.readDataValue(fieldName)+"  in "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in selectDropdownByIndex method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void selectDropdownByText(String fieldName)
	{
		try {
			Select dropdown = new Select(locator(fieldName));
			dropdown.selectByVisibleText(dataReader.readDataValue(fieldName));
			testReport.log(Status.INFO, "User Selected value : "+dataReader.readDataValue(fieldName)+"  in "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in selectDropdownByText method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}

	public void alertOk()
	{
		try {
			driver.switchTo().alert().accept();
			testReport.log(Status.INFO, "User Selected Alert Ok ");	
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in alertOk method :- "+e.getMessage());

		}
	}

	public void alertCancel()
	{
		try {
			driver.switchTo().alert().dismiss();
			testReport.log(Status.INFO, "User Selected Alert Cancel ");				
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in alertCancel method :- "+e.getMessage());
		}
	}

	public void alertTypeIn(String fieldName)
	{
		try {
			String data= dataReader.readDataValue(fieldName);
			driver.switchTo().alert().sendKeys(data);
			testReport.log(Status.INFO, "User Enter Value in Alert as "+data);		

		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in alertTypeIn method :- "+e.getMessage());
		}
	}

	public void switchToFrame(String fieldName)
	{
		try {
			driver.switchTo().frame(dataReader.readDataValue(fieldName));
			testReport.log(Status.INFO, "User switched to frame in "+fieldName);		
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in switchToFrame method :- "+e.getMessage());
		}
	}
	public void switchToFrameByXpath(String fieldName)
	{
		try {
			driver.switchTo().frame(locator(fieldName));
			testReport.log(Status.INFO, "User switched to frame in "+fieldName);		
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in switchToFrameByXpath method :- "+e.getMessage());
		}
	}

	public void backToMainFrame()
	{
		try {
			driver.switchTo().defaultContent();
			testReport.log(Status.INFO, "User switch back to frame ");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in backToMainFrame method :- "+e.getMessage());
		}
	}
	
	public void windowMaximize()
	{
		try {
			driver.manage().window().maximize();
			testReport.log(Status.INFO, "Maximize window");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in windowMaximize method :- "+e.getMessage());
		}
	}

	public void windowMinimize()
	{
		try {
			driver.manage().window().minimize();
			testReport.log(Status.INFO, "Minimize window");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in windowMinimize method :- "+e.getMessage());
		}
	}
	
	public void windowClose()
	{
		try {
			driver.close();
			testReport.log(Status.INFO, "Window Close");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in windowClose method :- "+e.getMessage());
		}
	}
	public void windowQuit()
	{
		try {
			driver.quit();
			testReport.log(Status.INFO, "Window Quit");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in windowQuit method :- "+e.getMessage());
		}
	}
	public void browserBack()
	{
		try {
			driver.navigate().back();
			testReport.log(Status.INFO, "Browser Back");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in browserBack method :- "+e.getMessage());
		}
	}
	public void browserRefresh()
	{
		try {
			driver.navigate().refresh();
			testReport.log(Status.INFO, "Browser Refresh");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in browserRefresh method :- "+e.getMessage());
		}
	}
	public void browserForward()
	{
		try {
			driver.navigate().forward();
			testReport.log(Status.INFO, "Browser forward");			
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in browserForward method :- "+e.getMessage());
		}
	}
	public void actionClick(String fieldName)
	{
		try {
			Actions action = new Actions(driver);
			action.click(locator(fieldName)).build().perform();
			testReport.log(Status.INFO, "User Clicked "+fieldName);	
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in actionClick method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void actionDoubleClick(String fieldName)
	{
		try {
			Actions action = new Actions(driver);
			action.doubleClick(locator(fieldName)).build().perform();
			testReport.log(Status.INFO, "User doubleClicked "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in actionDoubleClick method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void actionRightClick(String fieldName)
	{
		try {
			Actions action = new Actions(driver);
			action.contextClick(locator(fieldName)).build().perform();
			testReport.log(Status.INFO, "User Right Clicked "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in actionRightClick method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void actionClickAndHold(String fieldName)
	{
		try {
			Actions action = new Actions(driver);
			action.clickAndHold(locator(fieldName)).build().perform();
			testReport.log(Status.INFO, "User clickAndHold "+fieldName);			
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in actionClickAndHold method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void actionScrollToElement(String fieldName)
	{
		try {
			Actions action = new Actions(driver);
			action.scrollToElement(locator(fieldName)).build().perform();
			testReport.log(Status.INFO, "User scrollToElement "+fieldName);			
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in scrollToElement method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	public void actionMoveToElement(String fieldName)
	{
		try {
			Actions action = new Actions(driver);
			action.moveToElement(locator(fieldName)).build().perform();
			testReport.log(Status.INFO, "User moveToElement "+fieldName);
			takescreenShot(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			testReport.log(Status.ERROR, "Error Occured in moveToElement method :- "+e.getMessage());
			takescreenShot(fieldName);
		}
	}
	
	private void takescreenShot(String fileName)
	{
		String reportValue= dataReader.readConfigValue("screenshot");
		if(reportValue.toLowerCase().equals("yes"))
		{
			try {
				File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				File des= new File(".\\src\\test\\resources\\ScreenShot\\"+fileName+".png");
				FileUtils.copyFile(src, des);
				testReport.addScreenCaptureFromPath(des.getAbsolutePath(),fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private WebElement locator(String fieldName)
	{
		String locatorsValue= dataReader.readLocatorValue(fieldName);
		String[] locatorList = locatorsValue.split("#");
		switch (locatorList[0]) {
		case "xpath":
			return driver.findElement(By.xpath(locatorList[1]));
		case "id":
			return driver.findElement(By.id(locatorList[1]));
		case "name":
			return driver.findElement(By.name(locatorList[1]));
		case "class":
			return driver.findElement(By.className(locatorList[1]));
		case "css":
			return driver.findElement(By.cssSelector(locatorList[1]));
		case "link":
			return driver.findElement(By.linkText(locatorList[1]));
		default:
			return null;
		}

	}

}

