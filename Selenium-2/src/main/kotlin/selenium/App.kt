package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.*;
import java.util.Scanner;
fun main() {
    val sc = Scanner(System.`in`);
    val email:String? = sc.nextLine();
    println(email);
    val password:String? = System.console().readPassword("Enter password").toString();
    println(password);
    // "/home/psbakre/Downloads/geckodriver-v0.29.0-linux64/geckodriver"
    System.setProperty("webdriver.gecko.driver",
    "/home/psbakre/Downloads/geckodriver-v0.29.0-linux64/geckodriver");
    //{}.javaClass.getResource("/selenium/geckodriver").path);
    val driver: WebDriver = FirefoxDriver();
    val baseUrl: String = "https://google.com";
    driver.get(baseUrl);
    driver.findElement(By.xpath("//a[@class='gb_4 gb_5 gb_ae gb_4c']")).click();
    driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys()
    println(driver.getTitle());
    
}
