package com.crossover.e2e;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GMailTest extends TestCase {
    private WebDriver driver;
    private Properties properties = new Properties();

    public void setUp() throws Exception {
        
        properties.load(new FileReader(new File("src/test/resources/test.properties")));
        //Dont Change below line. Set this value in test.properties file incase you need to change it..
        System.setProperty("webdriver.chrome.driver",properties.getProperty("webdriver.chrome.driver") );
        driver = new ChromeDriver();
    }

    public void tearDown() throws Exception {
        driver.quit();
    }

    /*
     * Please focus on completing the task
     * 
     */
    @Test
    public void testSendEmail() throws Exception {
        driver.get("https://mail.google.com/");
       
        
        WebElement userElement = driver.findElement(By.id("identifierId"));
        userElement.sendKeys(properties.getProperty("username"));

        driver.findElement(By.id("identifierNext")).click();

        WebDriverWait wait=new WebDriverWait(driver,10);

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#passwordNext")));
		
		//Enter password
		driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys(properties.getProperty("password"));             

		//Click Next
        driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
        
        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS );
        driver.findElement(By.cssSelector(".aic .z0 div")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS );
        
        //Send to email address
        driver.findElement(By.cssSelector(".aic .z0 div")).sendKeys(String.format("%s@gmail.com", properties.getProperty("username")));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS );
        
        //email subject
        String str = properties.getProperty("email.subject");
        driver.findElement(By.cssSelector(".I5 .bAs .aoD.az6 .aoT")).sendKeys(str);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS );
        
        //email body
        driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys(properties.getProperty("email.body"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS );
        
        //send button
        driver.findElement(By.cssSelector(".T-I.J-J5-Ji.aoO.v7.T-I-atl.L3")).click();
        
    }
}
