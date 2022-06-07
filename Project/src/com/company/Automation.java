package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Automation {

    WebDriver driver;
    Actions action;
    WebElement[][] grid;


    public Automation(){}


    public  void main(String[] args){}


    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/ishaansareen/Downloads/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        grid = new WebElement[6][6];
    }


    public void launchApp(){
        driver.manage().window().maximize();
        driver.get("https://nerdlegame.com");
    }


    public  void clearCookies(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(15000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sp_message_iframe_633250")));
        driver.switchTo().frame("sp_message_iframe_633250");
        driver.findElement(By.xpath("//*[@id=\"notice\"]/div[5]/button[2]")).click();;
        driver.findElement(By.xpath("//*[@id=\"infoText\"]/div[2]/div[2]/button")).click();
    }

    // Makes the game board a 2d array of html elements for parsing
    public  void createBoard(){
        Actions action = new Actions(driver);
        WebElement[][] grid = new WebElement[6][6];
    }

    // writes equation using automation tool
    public void writeEquation(String a, String b, String c, String d, String e, String f, String g, String h){
        grid[0][0] = driver.findElement(By.cssSelector("#root > div > div.pb-grid > div:nth-child(1) > div:nth-child(1)"));
        action.moveToElement(grid[0][0]).click()
                .sendKeys(a).pause(100)
                .sendKeys(b).pause(100)
                .sendKeys(c).pause(100)
                .sendKeys(d).pause(100)
                .sendKeys(e).pause(100)
                .sendKeys(f).pause(100)
                .sendKeys(g).pause(100)
                .sendKeys(h).pause(100)
                .perform();
    }

    // hits the enter key
    public void deployEquation(){
        driver.findElement(By.cssSelector("#root > div > div:nth-child(4) > div:nth-child(2) > button:nth-child(6)")).click();
    }

    // takes an instance of the dynamic site for parsing with JSoup
    public void extractHTML(Parser p){
        p.setHTMLandDoc(driver.getPageSource());
    }
}
