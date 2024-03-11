package deti.tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import deti.tqs.Pages.HomePage;
import  deti.tqs.Pages.ChooseFlight;
import  deti.tqs.Pages.PurchasePage;
import  deti.tqs.Pages.ConfirmationPage;
import static io.github.bonigarcia.seljup.BrowserType.OPERA;
import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static io.github.bonigarcia.seljup.BrowserType.FIREFOX;
import static io.github.bonigarcia.seljup.BrowserType.EDGE;

import io.github.bonigarcia.seljup.DockerBrowser;


@ExtendWith(SeleniumJupiter.class)
public class PageObjectModelTest {

    //change type to test with other browsers
    @Test
    public void testPageObjectModel(@DockerBrowser(type = OPERA) WebDriver driver) {
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
