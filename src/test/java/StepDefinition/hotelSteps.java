package StepDefinition;

import Base.BaseTest;
import Pages.hotel;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;

public class hotelSteps extends BaseTest {
    hotel hot ;

    @Given("^App is lunched$")
    public void appIsLunched() throws MalformedURLException {
        setUp();
        hot = new hotel(driver);
    }

    @And("I Click on Image")
    public void iClickOnImage() {
        hot.ClickOnImage();
    }

    @When("I Filtered By Specific Hotels")
    public void iFilteredBySpecificHotels() {
        hot.HotelFilter();
    }

    @Then("I Should see All Hotel Results")
    public void iShouldSeeAllHotelResults() {
        hot.ShowHotelResults();
    }
}
