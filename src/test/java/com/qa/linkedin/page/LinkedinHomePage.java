package com.qa.linkedin.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinHomePage extends BasePageWeb{
	
	private Logger log = Logger.getLogger(LinkedinHomePage.class);
	//create a constructor
	
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a.nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(linkText="Sign in")
	private WebElement signinLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement signInHeading;
	
	@FindBy(id="username")
	private WebElement emailEditbox;
	
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement signInBtn;
	
	
	String linkedinHomePageTitle="LinkedIn: Log In or Sign Up";
	String linkedinSigninPageTitle="LinkedIn Login, Sign in | LinkedIn";
	public void verifyLinkedinHomePageTitle() {
		log.debug("verifying the linkedin in home page title:"+linkedinHomePageTitle);
		wait.until(ExpectedConditions.titleIs(linkedinHomePageTitle));
		Assert.assertEquals(driver.getTitle(), linkedinHomePageTitle);
	}

	public void verifyLinkedinSigninPageTitle() {
		log.debug("verifying the linkedin Signin page title:"+linkedinSigninPageTitle);
		wait.until(ExpectedConditions.titleIs(linkedinSigninPageTitle));
		Assert.assertEquals(driver.getTitle(), linkedinSigninPageTitle);
	}

	public void verifyLoginPageHeading() {
		log.debug("verifying the login page Heading in linkedin login page");
		wait.until(ExpectedConditions.visibilityOf(signInHeading));
		Assert.assertTrue(signInHeading.isDisplayed());
	}
	
	public void verifyLinkedinLogo() {
		log.debug("verifying the linkedinLogo  in linkedinhome page");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed());
	}
	
	
	public void clickSignInLink() throws InterruptedException {
		log.debug("Clicking on Sign in link in LinkedinHomePage");
		click(signinLink);
	}
	public void clickSignInButton() throws InterruptedException {
		log.debug("Clicking on Sign in button in LinkedinSigninPage");
		click(signInBtn);
	}
	
	
	public LinkedinLoggedinPage doLogin(String username,String pwd) throws InterruptedException {
		log.debug("Performing the linkedin login page ");
		log.debug("clear the content in emailEditbox");
		emailEditbox.clear();
		log.debug("entering the "+username+" in emailEditbox");
		sendKey(emailEditbox,username);
		log.debug("clear the content in passwordeditbox");
		passwordEditbox.clear();
		sendKey(passwordEditbox,pwd);
		clickSignInButton();
		return new LinkedinLoggedinPage();
	}
	
}
