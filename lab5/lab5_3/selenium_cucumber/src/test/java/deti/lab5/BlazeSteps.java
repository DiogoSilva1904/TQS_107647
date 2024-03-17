package deti.lab5;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BlazeSteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.get(url);
    }

    @And("I select {string} as the departure city")
    public void iSelectAsDepartureCity(String departureCity) {
        driver.findElement(By.name("fromPort")).sendKeys(departureCity);
    }

    @And("I select {string} as the destination city")
    public void iSelectAsDestinationCity(String destinationCity) {
        driver.findElement(By.name("toPort")).sendKeys(destinationCity);
    }

    @And("I click on the Find Flights button")
    public void iClickOnTheFindFlightsButton() {
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Then("I should see the header {string}")
    public void iShouldSee(String text) {
        String header = driver.findElement(By.tagName("h2")).getText();
        if (!header.equals(text)) {
            throw new NoSuchElementException("Header not found");
        }
    }

    @When("I select the flight number {int}")
    public void iSelectTheFlightNumber3(Integer number) {
       driver.findElement(By.cssSelector(String.format(".table > tbody:nth-child(2) > tr:nth-child(%d) > td:nth-child(2) > input:nth-child(1)",number))).click();
    }

    @Then("I should see the following {string}")
    public void iShouldSeeTheHeader(String text) {
        String header = driver.findElement(By.tagName("h3")).getText();
        if (!header.equals(text)) {
            throw new NoSuchElementException("Header not found");
        }
    }

    @When("I write my name as {string}")
    public void iWriteMyNameAs(String name) {
        driver.findElement(By.id("inputName")).sendKeys(name);
    }

    @And("I write my {string} as {string}")
    public void iWriteMyAs(String field, String value) {
        driver.findElement(By.id(field)).sendKeys(value);
    }

    @And("I select {string} as the card type")
    public void iSelectAsTheCardType(String cardType) {
        driver.findElement(By.id("cardType")).sendKeys(cardType);
    }

    @And("The name on the credit card is {string}")
    public void theNameOnTheCreditCardIs(String name) {
        driver.findElement(By.id("nameOnCard")).sendKeys(name);
    }

    @And("I click on the Purchase Flight button")
    public void iClickOnThePurchaseFlightButton() {
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheText(String text) {
        String header = driver.findElement(By.tagName("h1")).getText();
        if (!header.equals(text)) {
            throw new NoSuchElementException("Header not found");
        }
    }

    @Then("The title of the page should be {string}")
    public void theTitleOfThePageShouldBe(String title) {
        if (!driver.getTitle().equals(title)) {
            throw new NoSuchElementException("Title not found");
        }
    }






}
