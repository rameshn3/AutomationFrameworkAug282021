package com.qa.linkedin.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;
public class BrowsePage extends BasePageWeb{
private Logger log=Logger.getLogger(BrowsePage.class);

public BrowsePage(){

PageFactory.initElements(driver,this);
}



}
