package org.ui.test.modules;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ExecutionReport {


	public ExtentHtmlReporter report;
	public ExtentReports extent;
	public ExtentTest test;
	private String reportLocation="";

	public static Properties config = new Properties();


	public ExecutionReport() {
		try {
			config.load( new FileInputStream(".\\src\\test\\resources\\default.properties") );
			if (config.getProperty("cleanUpOutput").toString().contains("Y")) {
				fileDelete(new File(config.getProperty("reportPath").toString().replace("Optional[", "").replace("]", "")));
				fileDelete(new File(config.getProperty("responseDataPath").toString().replace("Optional[", "").replace("]", "")));
				fileDelete(new File(".\\src\\test\\resources\\ScreenShot"));
			}
			reportLocation = config.getProperty("reportPath").toString().replace("Optional[", "").replace("]", "");
			String suiteName = config.getProperty("suiteName").toString().replace("Optional[", "").replace("]", "");
			report = new ExtentHtmlReporter("./"+reportLocation+"/"+ suiteName + getDateTime() + ".html");
			report.config().setDocumentTitle(" UI Automation Report"); // Tile of report
			report.config().setReportName("UI Automation Testing"); // Name of the report
			report.config().setTheme(Theme.DARK);
			extent = new ExtentReports();
			extent.attachReporter(report);
			extent.setSystemInfo("Host name", "UI Tester");
			extent.setSystemInfo("Environment", "Testing");
			extent.setSystemInfo("user", "Tester");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	private String getDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
		LocalDateTime localDate = LocalDateTime.now();
		return dtf.format(localDate);
	}

	public void createTest(String testName){
		test = extent.createTest(testName);
	}

	public ExtentTest getTest(){
		return test;
	}

	public ExtentReports getReportInstance(){
		return extent;
	}
	
	private void fileDelete(File dir) {
        try {
            if(dir.exists()) {
                if (dir.isDirectory()) {
                    File[] files = dir.listFiles();
                    if (files != null && files.length > 0) {
                        for (File aFile : files) {
                            System.out.println("File " + aFile.getName() + " is deleted");
                            fileDelete(aFile);
                        }
                    }      
                }
                }
        }catch(Exception ex){
            if(ex.getCause().getMessage().contains("FileNotFoundException")){
                System.out.println("File directory given is not found");
                 }
            throw new RuntimeException(ex.getMessage());
        }
    }


}
