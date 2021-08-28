package com.qa.linkedin.testcases;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLoggedinPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase{
	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String excelPath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchData.xlsx";
	LinkedinHomePage lHmPage;
	LinkedinLoggedinPage llPage;
	SearchResultsPage srPage;
	
	@Test(description="setting the login for search")
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("Executing the doLoginTest()");
		lHmPage.verifyLinkedinHomePageTitle();
		lHmPage.verifyLinkedinLogo();
		lHmPage.clickSignInLink();
		lHmPage.verifyLinkedinSigninPageTitle();
		lHmPage.verifyLoginPageHeading();
		llPage = lHmPage.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));
	}
	
  @Test(dataProvider = "searchTestData",dependsOnMethods= {"doLoginTest"})
  public void searchTest(String s) throws Exception {
	log.debug("performing the search test");
	llPage.verifyProfileRailCard();
	srPage=llPage.doPeopleSearch(s);
	long count = srPage.getResultCount();
	log.debug("search results count for "+s+" keyword is:"+count);
	Thread.sleep(2000);
	srPage.clickHomeTab();
  }

  @DataProvider
  public Object[][] searchTestData() throws InvalidFormatException, IOException {
   Object[][] data=new ExcelUtils().getTestData(excelPath, "Sheet1");
      return data;
  }
  @BeforeClass
  public void initializeObject() {
	 log.debug("Initialize the objects");
	 lHmPage=new LinkedinHomePage();
	 llPage=new LinkedinLoggedinPage();
	 srPage=new SearchResultsPage();
  }

  @AfterClass
  public void logoutTest() throws InterruptedException {
	log.debug("Perform he logout");
	Thread.sleep(2000);
	llPage.doLogout();
  }

}
