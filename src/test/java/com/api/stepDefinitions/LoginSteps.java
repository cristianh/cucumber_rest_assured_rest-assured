package com.api.stepDefinitions;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class LoginSteps {

    WebDriver driver;
    int TIME_OUT= 10;
    String BASE_URL = "https://magento.softwaretestingboard.com";

    @Before
    public void setup(){
        driver = new ChromeDriver(); //instancia driver de tipo chromedriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1200,1400));
    }

    @Given("the user is on home page")
    public void the_user_is_on_home_page() {
        driver.get(BASE_URL);
    }

    @When("the user Navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        driver.findElement(By.linkText("Sign In")).click();
    }

    @When("the user enters a valid username and password")
    public void the_user_enters_a_valid_username_and_password(){
        driver.findElement(By.id("email")).sendKeys("nicoruiz23@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Humy!b$KTrV2");
        driver.findElement(By.id("send2")).submit();
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String user, String password) {
        driver.findElement(By.id("email")).sendKeys(user);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).submit();
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String user, String pass) {
        driver.findElement(By.id("email")).sendKeys(user);
        driver.findElement(By.id("pass")).sendKeys(pass);
        driver.findElement(By.id("send2")).submit();
    }

    @Then("the message  {string} should be displayed")
    public void the_message_should_be_displayed(String string) {
        //implementar el assertion
    }

    //@AfterStep
    public void takeScreenAfterStep(Scenario scenario){
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png", scenario.getName());
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
