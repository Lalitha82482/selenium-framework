package org.ui.test.executor;


import org.testng.annotations.*;
import org.ui.test.modules.Framework;

public class TestExecutor  {

	Framework actions= new Framework();
	
	@Test
	public void execution1()
	{
		actions.report.createTest("Fill Name");
		actions.testReport = actions.report.getTest();
		actions.browser();
		actions.windowMaximize();
		actions.application();
		actions.typeIn("FirstName");
		actions.typeIn("LastName");
	}
	@Test
	public void execution2()
	{
		actions.report.createTest("Fill Address");
		actions.testReport = actions.report.getTest();
		actions.typeIn("Address");
		actions.typeIn("Email Address");
	}
	@Test
	public void execution3()
	{
		actions.report.createTest("Fill Phone and Other details");
		actions.testReport = actions.report.getTest();
		actions.typeIn("Phone");
		actions.clickIn("Gender");
		actions.clickIn("Hobbies1");
		actions.clickIn("Hobbies2");
	}
	
	@AfterTest
	public void completeExecution(){
		actions.windowClose();
		actions.report.getReportInstance().flush();
	}
}