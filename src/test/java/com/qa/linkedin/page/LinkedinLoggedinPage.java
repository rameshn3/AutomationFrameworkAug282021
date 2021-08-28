package com.qa.linkedin.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinLoggedinPage extends BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);
	//create a constructor
	
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//img[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;
	
	@FindBy(xpath="//a[contains(.,'Sign Out')]")
	private WebElement signOutLink;
	
	@FindBy(xpath="//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditbox;
	
	public void verifyProfileRailCard() {
		log.debug("verifying the loggedin page profile rail card");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed());
	}
	
	public void doLogout() throws InterruptedException {
		log.debug("Performing the logout operation");
		wait.until(ExpectedConditions.visibilityOf(profileImageIcon));
		click(profileImageIcon);
		log.debug("wait for the visibility of signout link in the menu");
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		click(signOutLink);
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("performing the people search for :"+keyword);
		clearText(searchEditbox);
		log.debug("type the keyword:"+keyword+" in search editbox");
		sendKey(searchEditbox,keyword);
		Thread.sleep(2000);
		log.debug("Press the ENTER key");
		searchEditbox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}
	
}
