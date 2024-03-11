package org.example;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.openqa.selenium.chrome.ChromeDriver;

import org.example.Pages.HomePage;
import org.example.Pages.ChooseFlight;
import org.example.Pages.PurchasePage;
import org.example.Pages.ConfirmationPage;


public class PageObjectModelTest {



    @Test
    public void testPageObjectModel() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://blazedemo.com");
        HomePage homePage = new HomePage(driver);
        homePage.selecionarFromPort("Boston");
        homePage.selecionarToPort("London");
        ChooseFlight chooseFlight = homePage.clickButton();
        assertThat(chooseFlight.getTittle(), is(equalTo("Flights from Boston to London:")));
        PurchasePage purchasePage = chooseFlight.clickButton();
        purchasePage.setInputName("Diogo");
        purchasePage.setAddress("Rua 1");
        purchasePage.setCity("Aveiro");
        purchasePage.setState("Aveiro");
        purchasePage.setZipCode("1111-222");
        purchasePage.selectType("American Express");
        purchasePage.setCreditCardNumber("123456789");
        purchasePage.setCreditCardMonth("12");
        purchasePage.setCreditCardYear("2024");
        purchasePage.setNameOnCard("Diogo");
        purchasePage.clickRememberMe();
        ConfirmationPage confirmationPage = purchasePage.clickButton();
        assertThat(confirmationPage.getText(), is(equalTo("Thank you for your purchase today!")));
        assertThat(confirmationPage.getTittle(), is(equalTo("BlazeDemo Confirmation")));
        driver.quit();
    }
}
