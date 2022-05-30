package stepDefinations.unitedHomeSteps;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class BookHotel {
    LoginPage loginPage = new LoginPage();
    HomePage homePage =  new HomePage();




    @Then("{string} error message should be shown")
    public void errorMessageShouldBeShown(String arg0) throws Exception {
        if (arg0.contains("checkin"))
        {Assert.assertEquals(homePage.getTextFromElement(homePage.getInvalidCheckinmsg()),arg0);}
        else if (arg0.contains("checkout"))
        {Assert.assertEquals(homePage.getTextFromElement(homePage.getInvalidCheckoutmsg()),arg0);}
        else if (arg0.contains("length of stay"))
        {Assert.assertEquals(homePage.getTextFromElement(homePage.getInvalidStayLengthmsg()),arg0);}

    }

    @And("Enter checkin {string} date")
    public void enterCheckinDate(String arg0) {
        homePage.enterHotelCheckin(arg0);
    }

    @And("Enter checkout {string} date")
    public void enterCheckoutDate(String arg0) {
        homePage.enterHotelCheckout(arg0);
    }

    @And("Press find hotels")
    public void pressFindHotels() throws Exception {
        homePage.pressFindHotel();
    }

    
    @Then("a list of hotels with correct dates and location should be shown")
    public void aListOfHotelsWithCorrectDatesAndLocationShouldBeShown() {
    }

    @And("User selects Hotel tab")
    public void userSelectsHotelTab() throws Exception {
        homePage.selectBookmenu();
        homePage.clickHotelTab();
        homePage.waitForPageLoad();
    }

    @And("Enter no of rooms {string}")
    public void enterNoOfRooms(String arg0) throws Exception {
        homePage.selectNoOfRooms(arg0);
    }

    @And("Enter checkout {string} date where the no of days between two dates is greater than {int}")
    public void enterCheckoutDateWhereTheNoOfDaysBetweenTwoDatesIsGreaterThan(String arg0, int arg1) {
        homePage.enterHotelCheckout(arg0);
    }

    @When("Enter destination {string}")
    public void enterDestination(String arg0) throws Exception {
        homePage.enterHotelDestination(arg0);
    }

    @Then("a new tab opens with error message {string} or {string}")
    public void aNewTabOpensWithErrorMessageOr(String arg0, String arg1) throws Exception {
        String mainWindowHandle = homePage.driver.getWindowHandle();
        Set<String> allWindowHandles = homePage.driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        while (iterator.hasNext()) {
            String newTab=iterator.next();
            if (!newTab.equalsIgnoreCase(mainWindowHandle))
            {
                homePage.driver.switchTo().window(newTab);
                break;
            }
        }
        HomePage.waitForPageLoad();
        Assert.assertTrue(HomePage.isElementcurrentlyDisplayed(By.xpath("//div[@id='main-message']//*[contains(*,'"+arg1+"')]"))||HomePage.isElementcurrentlyDisplayed(By.xpath("//title[contains(*,\""+arg0+"\")]")));
    }
}
