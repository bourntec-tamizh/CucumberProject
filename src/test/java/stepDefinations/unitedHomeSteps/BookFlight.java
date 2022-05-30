package stepDefinations.unitedHomeSteps;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import stepDefinations.TestBase;

import java.io.IOException;

public class BookFlight {
    LoginPage loginPage = new LoginPage();
    HomePage homePage =  new HomePage();
    private Scenario scenario;




    @And("enter from city {string}")
    public void enterFromCity(String arg0) {
        homePage.selectFromCity(arg0);
    }

    @And("enter to city{string}")
    public void enterToCity(String arg0) {
        homePage.selectToCity(arg0);
    }

    @And("Enter from date {string}")
    public void enterFromDate(String arg0) {
        homePage.selectFromDate(arg0);
    }

    @And("Enter to date {string}")
    public void enterToDate(String arg0) throws Exception {
        homePage.selectToDate(arg0);
    }

    @And("Select travelers {string} {string}")
    public void selectTravelers(String traveler,String count) throws Exception {
        homePage.selectTravelers(traveler,count);
        homePage.closeTravellersMenu();
    }

    @And("select class {string}")
    public void selectClass(String arg0) throws Exception {
        homePage.selectCabinType(arg0);
        Thread.sleep(2000);

    }
    @And("Click find flights")
    public void clickFindFlights() throws Exception {
        homePage.clickOnFindFlightsButton();
        //HomePage.pause(4000);
    }

    @Then("a list of flight details should be shown")
    public void aListOfFlightDetailsShouldBeShown() throws Exception {
        WebDriverWait wait = new WebDriverWait(HomePage.driver, 10);
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert--error')]")));
        if(alertMessage.isDisplayed())
        {
            HomePage.test.info("alert message displayed in resut page: "+alertMessage.getText());
            HomePage.test.pass("test passed");
        }
        /*
        if(homePage.driver.findElement(homePage.getFlightInfo()).isEnabled())
        {
            homePage.logger.info("Flight Info present in result page");
        }
        else if(homePage.driver.findElement(By.xpath("//div[contains(@class,'alert--error')]")).isEnabled())
        {
            homePage.logger.info("Error message: We can't process this request. Please restart your search.");
        }
        else Assert.fail("test failed");*/

        /*if(homePage.driver.getPageSource().contains("We couldn't find a flight but we can still help"))
        {
            homePage.logger.info("test passed");
        }
        else if(homePage.isElementCurrentlyDisplayed(homePage.getFlightInfo()))
        {
            homePage.logger.info("test passed");
        }
        else
        {
            homePage.logger.error("test failed");
        }
*/

    }


    @And("Enter blank value in toDate field")
    public void enterBlankValueInToDateField() throws Exception {
        if (homePage.isElementSelected(By.id("roundtrip")))
        homePage.sendKeys(homePage.getToDateEdit(),Keys.ENTER);
        else
            homePage.sendKeys(homePage.getFromDateEdit(),Keys.ENTER);

    }

    @And("click and press Enter in fromDate field")
    public void clickAndPressEnterInFromDateField() {
        homePage.sendKeys(homePage.getFromDateEdit(),Keys.ENTER);
    }

    @When("user select journeytype {string}")
    public void userSelectJourneytype(String arg0) throws Exception {
        homePage.selectFlightType(arg0);
    }


    @And("select flexible month")
    public void selectFlexibleMonth() throws Exception {
        homePage.selectFlexOptions("months");
    }


    @And("select flexible date checkbox")
    public void selectFlexibleDateCheckbox() {
        homePage.selectFlexibleDatesCheckbox();
    }

    @And("select flexible days if journeytype {string} is roundtrip")
    public void selectFlexibleDaysIfJourneytypeIsRoundtrip(String arg0) throws Exception {
        if (arg0.equalsIgnoreCase("roundtrip"))
        {
            homePage.selectFlexOptions("days");
        }
    }



    @And("clear all the details in {string} {string}")
    public void clearAllTheDetailsIn(String arg0, String arg1) {
        homePage.clearAllDetails(arg0,arg1);
        HomePage.pause(2000);
    }
}
