package stepDefinations.unitedHomeSteps;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepDefinations.TestBase;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BookCar {
    LoginPage loginPage = new LoginPage();
    HomePage homePage =  new HomePage();
    Random rand=new Random();

    @When("Select car tab")
    public void selectCarTab() throws Exception {
        homePage.selectBookmenu();
        homePage.click(homePage.getBookCarTab());
        HomePage.pause(1000);

    }

    @And("Input pickup location {string}")
    public void inputPickupLocation(String arg0) throws Exception {
       // homePage.waitForElementToDisplay(homePage.getBookCarPickupInput(),1000);
        homePage.sendKeys(homePage.getBookCarPickupInput(),arg0);
        homePage.waitUntilElementIsClickable(homePage.getBookCarPickupInput_menu(),3000);
        List<WebElement> inputOptions=homePage.driver.findElements(homePage.getBookCarPickupInput_menu());
        homePage.actionClick(inputOptions.get(rand.nextInt(inputOptions.size())));
    }

    @And("Select return car to same location")
    public void selectReturnCarToSameLocation() throws Exception {
        if (!homePage.isElementSelected(homePage.getShowDropOffLocation()))
        {
            homePage.actionClick(homePage.getShowDropOffLocation());
        }
    }

    @Then("ensure Drop off location input field is disabled")
    public void ensureDropOffLocationInputFieldIsDisabled() throws Exception {
        Assert.assertFalse(homePage.isElementCurrentlyDisplayed(homePage.getBookCarDropoffInput()));
        HomePage.test.pass("drop off field is disabled");
    }


    @And("Select primary driver is {int} or older")
    public void selectPrimaryDriverIsOrOlder(int arg0) throws Exception {
        if (!homePage.isElementSelected(homePage.getHideAgeBox()))
        {
            homePage.click(homePage.getHideAgeBox());
            HomePage.test.info("primary driver checkbox selected");
        }
    }

    @Then("ensure Age field is disabled")
    public void ensureAgeFieldIsDisabled() throws Exception {
        Assert.assertFalse(homePage.isElementCurrentlyDisplayed(homePage.getDriversAge()));
        HomePage.test.pass("age field is disabled");
    }

    @When("select pickup time")
    public void selectPickupTime() throws Exception {
    List<WebElement> pickuptimes=homePage.driver.findElements(homePage.getPickupTimeOptions());
    int index= rand.nextInt(pickuptimes.size());
    homePage.click(homePage.getPickupTime());
    homePage.actionClick(pickuptimes.get(index));
    HomePage.test.info("pickuptime time selected as "+pickuptimes.get(index).getText());

    }

    @And("select drop off time")
    public void selectDropOffTime() throws Exception {
        List<WebElement> dropoffTimes=homePage.driver.findElements(homePage.getDropOffTimeOptions());
        int index= rand.nextInt(dropoffTimes.size());
        homePage.click(homePage.getDropoffTime());
        homePage.actionClick(dropoffTimes.get(index));
        HomePage.test.info("dropoff time selected as "+dropoffTimes.get(index).getText());
    }

    @And("Click find cars")
    public void clickFindCars() throws Exception {
        homePage.click(homePage.getFindCars());
        HomePage.test.info("find cars button clicked");
    }

    @Then("list of cars available shows in new tab")
    public void listOfCarsAvailableShowsInNewTab() throws Exception {
        String mainWindowHandle = homePage.driver.getWindowHandle();
        Set<String> allWindowHandles = homePage.driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();
        while (iterator.hasNext()) {
                String newTab=iterator.next();
                if (!newTab.equalsIgnoreCase(mainWindowHandle)) {
                homePage.driver.switchTo().window(newTab);
                HomePage.test.info("tab switched");
                break;
            }
        }

        System.out.println(homePage.waitForElementToDisplay(By.id("ct-search-results"),3000));
    }

    @And("unselect return car to same location")
    public void unselectReturnCarToSameLocation() throws Exception {
        if (homePage.isElementSelected(homePage.getShowDropOffLocation()))
        {
            homePage.actionClick(homePage.getShowDropOffLocation());
            HomePage.test.info("return car to same location checkbox unselected");
        }
    }

    @Then("ensure Drop off location input field is enabled")
    public void ensureDropOffLocationInputFieldIsEnabled() throws Exception {
        Assert.assertTrue(homePage.isElementCurrentlyDisplayed(homePage.getBookCarDropoffInput()));
        HomePage.test.info("drop off location field is enabled");
    }

    @And("Input drop off location {string}")
    public void inputDropOffLocation(String arg0) {

        homePage.sendKeys(homePage.getBookCarDropoffInput(),arg0);
        homePage.waitUntilElementIsClickable(homePage.getBookCarDropoffInput_menu(),3000);
        List<WebElement> inputOptions=homePage.driver.findElements(homePage.getBookCarDropoffInput_menu());
        homePage.actionClick(inputOptions.get(rand.nextInt(inputOptions.size())));
        HomePage.test.info("drop off location entered as "+arg0);
    }

    @And("unselect primary driver is {int} or older")
    public void unselectPrimaryDriverIsOrOlder(int arg0) throws Exception {
        if (homePage.isElementSelected(homePage.getHideAgeBox()))
        {
            homePage.actionClick(homePage.getHideAgeBox());
            HomePage.test.info("primary driver checkbox unselected");
        }
    }

    @Then("ensure Age field is enabled")
    public void ensureAgeFieldIsEnabled() throws Exception {
        Assert.assertTrue(homePage.isElementCurrentlyDisplayed(homePage.getDriversAge()));
        HomePage.test.info("Age field is enabled");
    }

    @When("enter driver age {string}")
    public void enterDriverAge(String arg0) {
        homePage.sendKeys(homePage.getDriversAge(),arg0);
        HomePage.test.info("driver's age entered as "+arg0);
    }

    @Then("ensure below error messages are shown")
    public void ensure_below_error_messages_are_shown(DataTable dataTable) throws Exception {
        By errorMsgLocator;
        List<List<String>> errorMessages=dataTable.asLists();
        for (List<String> errorMessage : errorMessages) {
            for (String msg:errorMessage)
            {
                errorMsgLocator=By.xpath("//span[contains(text(),'"+msg+"')]");
                Assert.assertTrue(homePage.driver.findElement(errorMsgLocator).getText().contains(msg));
                HomePage.test.pass(msg+" is present.");

            }

        }

    }
    @And("enter drop off date {string}")
    public void enterDropOffDate(String arg0) {
        homePage.sendKeys(homePage.getBookCarDropoffDate(),arg0);
        HomePage.test.info("drop off date entered as "+arg0);

    }

    @When("enter Pick up date {string}")
    public void enterPickUpDate(String arg0) {
        homePage.sendKeys(homePage.getBookCarPickupDate(),arg0);
        HomePage.test.info("pickup date entered as "+arg0);
    }
}
