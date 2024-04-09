package com.tqs107647.homework.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.invoke.MethodHandles;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
public class MakeReservation {

    private Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private WebDriver driver;

    @Given("the user is on the bus trip reservation page")
    public void iAmOnTheReservationPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/trips");
    }

    @When("the user writes the departure city as {string}")
    public void iWriteTheDepartureCity(String departureCity) {
        driver.findElement(By.id("origin-box")).sendKeys(departureCity);
    }

    @And("the user writes the arrival city as {string}")
    public void iWriteTheDestinationCity(String destinationCity) {
        driver.findElement(By.id("destination-box")).sendKeys(destinationCity);
    }

    @And("the user selects the first trip")
    public void iWriteTheDepartureDate() {
        WebElement firstTrip = driver.findElement(By.xpath("//table[@id='trip-table']/tbody/tr[1]"));
        firstTrip.click();
    }

    @Then("the user should see the trip details")
    public void iShouldSeeTheTripDetails() {
       driver.findElement(By.id("button-details")).click();
    }

    @When("the user clicks on the button to make a reservation")
    public void iClickOnTheButtonToMakeAReservation() {
        driver.findElement(By.id("create-button")).click();
    }


    @When("the user writes the name as {string}")
    public void iWriteTheName(String name) {
        driver.findElement(By.id("userName")).sendKeys(name);
    }

    @And("the user chooses the seat number {string}")
    public void iChooseTheSeatNumber(String seatNumber) {
        driver.findElement(By.id("seat")).sendKeys(seatNumber);
    }

    @And("the user writes the email as {string}")
    public void iWriteTheEmail(String email) {
        driver.findElement(By.id("userEmail")).sendKeys(email);
    }

    @And("the user writes the nif as {string}")
    public void iWriteTheNif(String nif) {
        driver.findElement(By.id("nif")).sendKeys(nif);
    }

    @When("the user clicks on the button to confirm the reservation")
    public void iClickOnTheButtonToConfirmTheReservation() {
        driver.findElement(By.id("submit-button")).click();
    }

    @Then("the user should see the reservation confirmation message")
    public void iShouldSeeTheReservationConfirmation() {
        WebElement confirmation = driver.findElement(By.id("end-text"));
        assertEquals("Thank you for your purchase.", confirmation.getText());
    }
}
